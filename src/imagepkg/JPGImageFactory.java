package imagepkg;

import java.util.List;

/**
 * implementation of ImageFactory that creates a JPG image object.
 */
public class JPGImageFactory implements ImageFactory {
  @Override
  public Image createImage(int width, int height, List<Pixel> pixelList, String outputImageName) {
    return new JPGImage(width, height, pixelList, outputImageName);
  }
}
