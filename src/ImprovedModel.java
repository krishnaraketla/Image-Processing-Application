import java.io.IOException;

import filter.ImageFilter;
import filter.ImageTransform;
import imagepkg.Image;

/**
 * An extension of the Model interface that adds additional image processing functionality.
 */
public interface ImprovedModel extends Model {
  /**
   * Applies the given image filter to the specified input image and saves the result
   * to the specified output image file.
   *
   * @param filter      the image filter to apply
   * @param imageName   the name of the input image file
   * @param outputImage the name of the output image file
   * @throws IOException if there is an error reading or writing the image file
   */
  void applyFilter(ImageFilter filter, String imageName, String outputImage) throws IOException;

  /**
   * Transforms the specified input image using the given image transform and saves the result
   * to the specified output image file.
   *
   * @param transform   the image transform to apply
   * @param imageName   the name of the input image file
   * @param outputImage the name of the output image file
   * @throws IOException if there is an error reading or writing the image file
   */
  void transform(ImageTransform transform, String imageName, String outputImage) throws IOException;

  /**
   * Applies the dithering algorithm to the specified input image and saves the result to the
   * specified output image file.
   *
   * @param inputImageName  the name of the input image file
   * @param outputImageName the name of the output image file
   * @throws IOException if there is an error reading or writing the image file
   */
  void dither(String inputImageName, String outputImageName) throws IOException;

  /**
   * Saves the specified image as a PPM file with the specified filename.
   *
   * @param filename the name of the PPM file to create
   * @param image    the image to save
   * @throws IOException if there is an error writing the image file
   */
  void saveAsPPM(String filename, Image image) throws IOException;

  /**
   * Saves the specified image as a JPEG file with the specified filename.
   *
   * @param filename the name of the JPEG file to create
   * @param image    the image to save
   * @throws IOException if there is an error writing the image file
   */
  void saveAsJPG(String filename, Image image) throws IOException;

  /**
   * Saves the specified image as a BMP file with the specified filename.
   *
   * @param filename the name of the BMP file to create
   * @param image    the image to save
   * @throws IOException if there is an error writing the image file
   */
  void saveAsBMP(String filename, Image image) throws IOException;

  /**
   * Saves the specified image as a PNG file with the specified filename.
   *
   * @param filename the name of the PNG file to create
   * @param image    the image to save
   * @throws IOException if there is an error writing the image file
   */
  void saveAsPNG(String filename, Image image) throws IOException;
}


