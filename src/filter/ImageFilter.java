package filter;

import java.util.List;

import imagepkg.Image;
import imagepkg.Pixel;

/**
 * Represents a filter matrix that can be applied to an image to modify it.
 */
public interface ImageFilter {
  /**
   * Fetch the kernel matrix that is to be applied on the image.
   *
   * @return double matrix object
   */
  double[][] getKernel();

  /**
   * Apply the filter on the image to perform pixel manipulator.
   *
   * @param inputImage input image
   * @return Image object after filter is passed
   */
  List<Pixel> apply(Image inputImage);
}

