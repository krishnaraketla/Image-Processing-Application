import java.io.IOException;

/**
 * An extension of the Model interface that adds additional image processing functionality.
 */
public interface EnchancedModel extends ImprovedModel {
  /**
   * Generates a histogram for the specified image and saves to a csv file.
   *
   * @param imageName the name of the image file
   * @throws IOException if there is an error reading the image file
   */
  void generateHistogram(String imageName) throws IOException;
}
