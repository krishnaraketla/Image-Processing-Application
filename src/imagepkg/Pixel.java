package imagepkg;

/**
 * Represents  single pixel in an Image.Image.
 */
public class Pixel {
  private final int r;
  private final int g;
  private final int b;

  // @todo: make sure only valid values are allowed.

  /**
   * Constructor to create a Image.Image.Pixel Object with Red, Green, Blue values.
   *
   * @param r intensity of Red component
   * @param g intensity of Green component
   * @param b intensity of Blue component
   */
  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Fetches the maximum value of the 3 components.
   *
   * @return Maximum value in Double
   */
  public double getValue() {
    return Math.max(b, Math.max(r, g));
  }

  /**
   * Calculates the average of the three components in the pixel.
   *
   * @return average in Double
   */
  public double getIntensity() {
    return (r + g + b) / 3;
  }

  /**
   * Calculates a weighted sum of the three components.
   *
   * @return Double value of weigthed sum
   */
  public double getLuma() {
    return 0.2162 * r + 0.7152 * g + 0.0722 * b;
  }

  /**
   * Get the value of the red component in the pixel.
   *
   * @return red value int
   */
  public int getRed() {
    return r;
  }

  /**
   * Get the value of the green component in the pixel.
   *
   * @return green value int
   */
  public int getGreen() {
    return g;
  }

  /**
   * Get the value of the blue component in the pixel.
   *
   * @return blue value int
   */
  public int getBlue() {
    return b;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Pixel)) {
      return false;
    }
    Pixel other = (Pixel) o;
    boolean equals = false;
    if (this.getRed() == other.getRed()) {
      if (this.getBlue() == other.getBlue()) {
        if (this.getGreen() == other.getGreen()) {
          equals = true;
        }
      }
    }
    return equals;
  }

  @Override
  public final int hashCode() {
    return r + g + b;
  }
}
