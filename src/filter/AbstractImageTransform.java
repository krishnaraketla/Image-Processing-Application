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
 * Abstract class that hold implementation common to all Transform operations.
 */
public abstract class AbstractImageTransform implements ImageTransform {
  protected Map<ImageType, ImageFactory> imageFactories;

  /**
   * Constructor to instantiate image factory.
   */
  public AbstractImageTransform() {
    imageFactories = new HashMap<>();
    imageFactories.put(ImageType.PPM, new PPMImageFactory());
    imageFactories.put(ImageType.JPG, new JPGImageFactory());
    imageFactories.put(ImageType.BMP, new BMPImageFactory());
    imageFactories.put(ImageType.PNG, new PNGImageFactory());
  }

  @Override
  public List<Pixel> apply(Image source) {
    double[][] kernel = this.getKernel();
    List<Pixel> transformedPixelList = new ArrayList<>();

    // Iterate over each pixel in the source image
    for (Pixel pixel : source.getPixels()) {
      // Extract the red, green, and blue values from the pixel
      int r = pixel.getRed();
      int g = pixel.getGreen();
      int b = pixel.getBlue();

      // Apply the kernel matrix to the pixel values
      int rPrime = (int) (kernel[0][0] * r + kernel[0][1] * g + kernel[0][2] * b);
      int gPrime = (int) (kernel[1][0] * r + kernel[1][1] * g + kernel[1][2] * b);
      int bPrime = (int) (kernel[2][0] * r + kernel[2][1] * g + kernel[2][2] * b);

      // Clamp the pixel values to the range [0, 255]
      rPrime = Math.max(0, Math.min(255, rPrime));
      gPrime = Math.max(0, Math.min(255, gPrime));
      bPrime = Math.max(0, Math.min(255, bPrime));

      // Create a new pixel with the transformed values
      Pixel transformedPixel = new Pixel(rPrime, gPrime, bPrime);

      // Add the transformed pixel to the result image
      transformedPixelList.add(transformedPixel);
    }

    return transformedPixelList;
  }
}
