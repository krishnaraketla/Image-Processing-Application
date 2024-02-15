import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import imagepkg.Image;
import imagepkg.Pixel;

/**
 * Represents an implementation of Model.
 */
public class ModelImpl extends AbstractModel {

  @Override
  public void generateHistogram(String imageName) throws IOException {
    System.out.println("Generating histogram for image: " + imageName);
    // Initialize arrays to store histogram data for each component (r, g, b)
    int[] histogramR = new int[256];
    int[] histogramG = new int[256];
    int[] histogramB = new int[256];
    // Initialize an array to store histogram data for the intensity (greyscale) values
    int[] histogram = new int[256];

    Image image = getImage(imageName);
    if (image == null) {
      System.out.println("Invalid image: " + imageName);
      throw new IOException("Invalid image: " + imageName);
    }

    // Loop over all pixels in the image and update the histograms
    List<Pixel> pixels = image.getPixels();
    for (Pixel pixel : pixels) {
      histogramR[pixel.getRed()]++;
      histogramG[pixel.getGreen()]++;
      histogramB[pixel.getBlue()]++;
      int intensity = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
      histogram[intensity]++;
    }

    // Write the histogram data to a file
    String outputFileName = "temp/histogram.csv";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
      // Write the header
      writer.write("Value,R,G,B,Intensity\n");

      // Write the histogram data for each value
      for (int i = 0; i < 256; i++) {
        writer.write(i + "," + histogramR[i] + "," + histogramG[i] + "," + histogramB[i] + ","
                + histogram[i] + "\n");
      }
    } catch (IOException e) {
      System.err.println("Error writing histogram data to file: " + e.getMessage());
    }
  }
}
