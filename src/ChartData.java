import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data structure for storing chart data.
 */
public class ChartData {
  private int[] values;
  private int[] reds;
  private int[] greens;
  private int[] blues;
  private int[] intensities;

  /**
   * Constructor for ChartData.
   *
   * @param values      values
   * @param reds        reds
   * @param greens      greens
   * @param blues       blues
   * @param intensities intensities
   */
  public ChartData(int[] values, int[] reds, int[] greens, int[] blues, int[] intensities) {
    this.values = values;
    this.reds = reds;
    this.greens = greens;
    this.blues = blues;
    this.intensities = intensities;
  }

  /**
   * Returns the values.
   *
   * @return the values
   */
  public int[] getValues() {
    return values;
  }

  /**
   * Returns the reds.
   *
   * @return the reds
   */
  public int[] getReds() {
    return reds;
  }

  /**
   * Returns the greens.
   *
   * @return the greens
   */
  public int[] getGreens() {
    return greens;
  }

  /**
   * Returns the blues.
   *
   * @return the blues
   */
  public int[] getBlues() {
    return blues;
  }

  /**
   * Returns the intensities.
   *
   * @return the intensities
   */
  public int[] getIntensities() {
    return intensities;
  }

  /**
   * Returns a ChartData object from the specified file.
   *
   * @param fileName the name of the file
   * @return a ChartData object
   * @throws IOException if there is an error reading the file
   */
  public static ChartData getDataFromFile(String fileName) throws IOException {
    List<Integer> valueList = new ArrayList<>();
    List<Integer> redList = new ArrayList<>();
    List<Integer> greenList = new ArrayList<>();
    List<Integer> blueList = new ArrayList<>();
    List<Integer> intensityList = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line = reader.readLine();
      line = reader.readLine(); // Skip the header line
      while (line != null) {
        String[] parts = line.split(",");
        int value = Integer.parseInt(parts[0]);
        int red = Integer.parseInt(parts[1]);
        int green = Integer.parseInt(parts[2]);
        int blue = Integer.parseInt(parts[3]);
        int intensity = Integer.parseInt(parts[4]);

        valueList.add(value);
        redList.add(red);
        greenList.add(green);
        blueList.add(blue);
        intensityList.add(intensity);

        line = reader.readLine();
      }
    }

    int[] values = valueList.stream().mapToInt(Integer::intValue).toArray();
    int[] reds = redList.stream().mapToInt(Integer::intValue).toArray();
    int[] greens = greenList.stream().mapToInt(Integer::intValue).toArray();
    int[] blues = blueList.stream().mapToInt(Integer::intValue).toArray();
    int[] intensities = intensityList.stream().mapToInt(Integer::intValue).toArray();


    return new ChartData(values, reds, greens, blues, intensities);
  }
}
