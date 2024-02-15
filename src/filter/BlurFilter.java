package filter;

/**
 * Represents a GreyScale which when applied to an image, makes it greyscale.
 */
public class BlurFilter extends AbstractImageFilter {

  @Override
  public double[][] getKernel() {
    double[][] blurFilter = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
    return blurFilter;
  }
}
