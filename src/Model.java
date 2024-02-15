import java.io.IOException;
import java.util.List;

import imagepkg.Image;

/**
 * Represents the model for image manipulation and enhancement.
 * Describes all the operations that can be performed by the application.
 * A single model instance represents operations performed in a single user session.
 */
public interface Model {

  /**
   * loads the image from file.
   *
   * @param filename  filepath that contains the image
   * @param imageName name of image to be loaded as
   */
  void loadImage(String filename, String imageName) throws IOException;

  /**
   * Save image to file.
   *
   * @param fileName  nfilepath of image.
   * @param imageName name of image to be saved.
   * @throws IOException throws IOE
   */
  void saveImage(String fileName, String imageName) throws IOException;

  /**
   * Flips an image horizontally.
   *
   * @param imageName       Image.Image that needs to be flipped
   * @param outputImageName A flipped Image.Image
   * @throws IOException throws IOE
   */
  void flipHorizontal(String imageName, String outputImageName) throws IOException;

  /**
   * Flips an image vertically.
   *
   * @param imageName       Name of image that needs to be flipped
   * @param outputImageName flipped Image.Image
   * @throws IOException throws IOE
   */
  void flipVertical(String imageName, String outputImageName) throws IOException;

  /**
   * Increase image brightness by a scale factor.
   *
   * @param value           scale by which brightness has to be increased
   * @param imageName       input image
   * @param outputImageName brightened image
   * @throws IOException throws IOE
   */
  void brighten(int value, String imageName, String outputImageName) throws IOException;

  /**
   * Decrease image brightness by a scale factor.
   *
   * @param value           scale by which brightness has to be increased
   * @param imageName       input image
   * @param outputImageName darkened image
   * @throws IOException throws IOE
   */
  void darken(int value, String imageName, String outputImageName) throws IOException;

  /**
   * Convert image to greyscale using red component.
   *
   * @param imageName       Source Image.Image
   * @param outputImageName Greyscale image
   * @throws IOException throws IOE
   */
  void greyscaleRedComponent(String imageName, String outputImageName) throws IOException;

  /**
   * Convert image to greyscale using blue component.
   *
   * @param imageName       Source Image.Image
   * @param outputImageName Greyscale image
   * @throws IOException throws IOE
   */
  void greyscaleBlueComponent(String imageName, String outputImageName) throws IOException;

  /**
   * Convert image to greyscale using green component.
   *
   * @param imageName       Source Image.Image
   * @param outputImageName Greyscale image
   * @throws IOException throws IOE
   */
  void greyscaleGreenComponent(String imageName, String outputImageName) throws IOException;

  /**
   * Convert image to greyscale using value component.
   *
   * @param imageName       Source Image.Image
   * @param outputImageName Greyscale image
   * @throws IOException throws IOE
   */
  void greyscaleValueComponent(String imageName, String outputImageName) throws IOException;

  /**
   * Convert image to greyscale using luma component.
   *
   * @param imageName       Source Image.Image
   * @param outputImageName Greyscale image
   * @throws IOException throws IOE
   */
  void greyscaleLumaComponent(String imageName, String outputImageName) throws IOException;

  /**
   * Combine three greyscale image into a single color image whose R,G,B values come from the
   * three images.
   *
   * @param destImageName output image
   * @param image1        first image
   * @param image2        second image
   * @param image3        third image
   * @throws IOException throws IOE
   */
  void combine(String destImageName, String image1, String image2, String image3)
          throws IOException;

  /**
   * Split a single image into 3 images representing each of the three channels.
   *
   * @param imageName  source image to be split
   * @param imageRed   red channel image
   * @param imageGreen green channel image
   * @param imageBlue  blue channel image
   * @throws IOException throws IOE
   */
  void split(String imageName, String imageRed, String imageGreen, String imageBlue)
          throws IOException;

  /**
   * Returns a list of images in current session.
   *
   * @return List of images maintained by model
   */
  List<Image> getImages();
}
