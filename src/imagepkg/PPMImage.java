package imagepkg;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an image in PPM format.
 * An image holds a list of Pixels that represents each row of the image
 */
public class PPMImage extends AbstractImage {

  /**
   * Construct image from a given list of pixels.
   *
   * @param width width of image
   * @param height height of image
   * @param pixelList list of all pixels
   */
  public PPMImage(int width, int height, List<Pixel> pixelList, String imageName) {
    this.width = width;
    this.height = height;
    this.pixelList = pixelList;
    this.imageName = imageName;
    this.imageType = ImageType.PPM;
  }

  /**
   * Construct image from a given file of ppm format.
   *
   * @param filename filepath of image
   */
  public PPMImage(String filename, String imageName) {
    this.imageType = ImageType.PPM;
    pixelList = new ArrayList<>();
    this.imageName = imageName;
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      // @todo: throw exception here
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    this.width = sc.nextInt();
    this.height = sc.nextInt();

    int maxValue = sc.nextInt();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixelList.add(new Pixel(r, g, b));
      }
    }
    System.out.println("HERE");
  }

  @Override
  public void saveImage(String fileName) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    writer.write("P3\n");
    writer.write("# " + fileName + "\n");
    writer.write(this.getWidth() + " " + this.getHeight() + "\n");
    writer.write("255\n");
    for (Pixel pixel : this.getPixels()) {
      writer.write(pixel.getRed() + " ");
      writer.write(pixel.getGreen() + " ");
      writer.write(pixel.getBlue() + "\n");
    }
    writer.close();
  }
}
