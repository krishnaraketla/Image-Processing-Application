package filter;

/**
 * Represents a Sepia filter that can be used to transform image.
 */
public class SepiaFilter extends AbstractImageTransform {
  @Override
  public double[][] getKernel() {
    double[][] filter = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };
    return filter;
  }
}
