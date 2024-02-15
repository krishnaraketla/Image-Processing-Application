package imagepkg;

import java.io.IOException;
import java.util.List;

/**
 * Represents an image in PPM format.
 * An image holds a list of Pixels that represents each row of the image
 */
public class JPGImage extends AbstractImage {

  /**
   * Construct image from a given list of pixels.
   *
   * @param width     width of image
   * @param height    height of image
   * @param pixelList list of all pixels
   */
  public JPGImage(int width, int height, List<Pixel> pixelList, String imageName) {
    this.width = width;
    this.height = height;
    this.pixelList = pixelList;
    this.imageName = imageName;
    this.imageType = ImageType.JPG;
  }

  /**
   * Constructor to load a JPG image from file.
   *
   * @param filename  name of file
   * @param imageName name of image being loaded
   * @throws IOException throw exception if file invalid
   */
  public JPGImage(String filename, String imageName) throws IOException {
    this.imageType = ImageType.JPG;
    loadFromFile(filename, imageName);
  }
}
