package imagepkg;

import java.io.IOException;
import java.util.List;

/**
 * Represents an image.
 * An image holds a list of Pixels that represents each row of the image
 */
public interface Image {

  /**
   * Get the image name.
   *
   * @return Strinng imageName
   */
  String getImageName();

  /**
   * Get the list of all pixels in the image.
   *
   * @return List of Image.Image.Pixel
   */
  List<Pixel> getPixels();

  /**
   * Get Width of the image.
   *
   * @return int width
   */
  int getWidth();

  /**
   * Get Height of the image.
   *
   * @return int height
   */
  int getHeight();

  /**
   * Save image to file.
   *
   * @param filename name of file
   * @throws IOException throw exception if image or filepath is invalid
   */
  void saveImage(String filename) throws IOException;

  ImageType getImageType();
}
