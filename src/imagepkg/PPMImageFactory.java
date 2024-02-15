package imagepkg;

import java.util.List;

/**
 * implementation of ImageFactory that creates a PPM image object.
 */
public class PPMImageFactory implements ImageFactory {
  @Override
  public Image createImage(int width, int height, List<Pixel> pixelList, String outputImageName) {
    return new PPMImage(width, height, pixelList, outputImageName);
  }
}
