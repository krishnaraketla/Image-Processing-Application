package imagepkg;

import java.util.List;

/**
 * ImageFactory is used to generate Image objects specific to user need.
 */
public interface ImageFactory {
  /**
   * Create an Image object by calling implemetation specific constructor.
   *
   * @param width           width of image being created
   * @param height          height of image being created
   * @param pixelList       list of pixels in image
   * @param outputImageName name of image being created
   * @return Image object
   */
  Image createImage(int width, int height, List<Pixel> pixelList, String outputImageName);
}
