package filter;

/**
 * Represents a sharpen filter that can be used to sharpen the image by transforming it.
 */
public class SharpenFilter extends AbstractImageFilter {
  @Override
  public double[][] getKernel() {
    double[][] sharpenFilter = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125},
    };
    return sharpenFilter;
  }
}
