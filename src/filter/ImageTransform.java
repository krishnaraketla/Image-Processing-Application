package filter;

import java.util.List;

import imagepkg.Image;
import imagepkg.Pixel;

/**
 * Represents an image trasnform operation that can be performed using a specific filter.
 */
public interface ImageTransform {
  /**
   * Fetch the kernel matrix that is used to perform transform operation.
   *
   * @return double matrix
   */
  double[][] getKernel();

  /**
   * Apply the kernel matrix to the image.
   *
   * @param source Input image
   * @return Image object
   */
  List<Pixel> apply(Image source);
}
