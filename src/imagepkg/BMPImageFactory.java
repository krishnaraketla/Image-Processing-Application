package imagepkg;

import java.util.List;

/**
 * implementation of ImageFactory that creates a BMP image object.
 */
public class BMPImageFactory implements ImageFactory {
  @Override
  public Image createImage(int width, int height, List<Pixel> pixelList, String outputImageName) {
    return new BMPImage(width, height, pixelList, outputImageName);
  }
}
