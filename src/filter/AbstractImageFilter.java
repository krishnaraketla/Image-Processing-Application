package filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imagepkg.BMPImageFactory;
import imagepkg.Image;
import imagepkg.ImageFactory;
import imagepkg.ImageType;
import imagepkg.JPGImageFactory;
import imagepkg.PNGImageFactory;
import imagepkg.PPMImageFactory;
import imagepkg.Pixel;

/**
 * Contains all common implementation of ImageFilter interface.
 */
public abstract class AbstractImageFilter implements ImageFilter {

  protected Map<ImageType, ImageFactory> imageFactories;

  /**
   * Constructor that initialises image factory dictionary.
   */
  public AbstractImageFilter() {
    imageFactories = new HashMap<>();
    imageFactories.put(ImageType.PPM, new PPMImageFactory());
    imageFactories.put(ImageType.JPG, new JPGImageFactory());
    imageFactories.put(ImageType.BMP, new BMPImageFactory());
    imageFactories.put(ImageType.PNG, new PNGImageFactory());
  }

  @Override
  public List<Pixel> apply(Image inputImage) {
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    List<Pixel> pixels = inputImage.getPixels();
    double[][] kernel = this.getKernel();

    List<Pixel> filteredPixels = new ArrayList<>();

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int index = y * width + x;

        double red = 0.0;
        double green = 0.0;
        double blue = 0.0;

        for (int i = 0; i < kernel.length; i++) {
          for (int j = 0; j < kernel[i].length; j++) {
            int pixelX = x + j - 1;
            int pixelY = y + i - 1;

            if (pixelX < 0 || pixelX >= width || pixelY < 0 || pixelY >= height) {
              continue;
            }

            Pixel pixel = pixels.get(pixelY * width + pixelX);
            red += pixel.getRed() * kernel[i][j];
            green += pixel.getGreen() * kernel[i][j];
            blue += pixel.getBlue() * kernel[i][j];
          }
        }

        int filteredRed = (int) Math.min(Math.max(red, 0), 255);
        int filteredGreen = (int) Math.min(Math.max(green, 0), 255);
        int filteredBlue = (int) Math.min(Math.max(blue, 0), 255);

        Pixel filteredPixel = new Pixel(filteredRed, filteredGreen, filteredBlue);
        filteredPixels.add(filteredPixel);
      }
    }
    return filteredPixels;
  }
}
