package imagepkg;

import java.util.List;

/**
 * implementation of ImageFactory that creates a PNG image object.
 */
public class PNGImageFactory implements ImageFactory {
  @Override
  public Image createImage(int width, int height, List<Pixel> pixelList, String outputImageName) {
    return new PNGImage(width, height, pixelList, outputImageName);
  }
}
