package filter;

/**
 * Represents a GreyScale which when applied to an image, makes it greyscale.
 */
public class GreyScaleFilter extends AbstractImageTransform {
  @Override
  public double[][] getKernel() {
    double[][] filter = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}
    };
    return filter;
  }
}
