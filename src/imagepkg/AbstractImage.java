package imagepkg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * An abstract image class.
 * Describes all functionality that are common to all tyes of images
 */
public abstract class AbstractImage implements Image {
  protected String imageName;
  protected int width;
  protected int height;
  protected List<Pixel> pixelList;

  protected ImageType imageType;


  /**
   * Load image from file into an Image object.
   * Uses the ImageIO class to read from file.
   *
   * @param filename  name of input file
   * @param imageName name of image that is being loaded
   * @throws IOException throws exception if file is invalid
   */
  protected void loadFromFile(String filename, String imageName) throws IOException {
    this.imageName = imageName;
    BufferedImage image = ImageIO.read(new File(filename));
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.pixelList = new ArrayList<>();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        pixelList.add(new Pixel(red, green, blue));
      }
    }
  }

  @Override
  public String getImageName() {
    return imageName;
  }

  @Override
  public List<Pixel> getPixels() {
    return pixelList;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public ImageType getImageType() {
    return imageType;
  }

  @Override
  public void saveImage(String filename) throws IOException {
    // Create BufferedImage from pixel data
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = getPixels().get(y * width + x);
        int rgb = pixel.getRed() << 16 | pixel.getGreen() << 8 | pixel.getBlue();
        image.setRGB(x, y, rgb);
      }
    }

    // Save BufferedImage to file using ImageIO
    File file = new File(filename);
    ImageIO.write(image, getImageType().toString(), file);
  }

}