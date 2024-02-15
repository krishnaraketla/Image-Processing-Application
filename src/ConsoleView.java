import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation of View interface that represents a Console View.
 */
public class ConsoleView extends AbstractView {

  /**
   * Constructor to Initialise the Model objects and prepare for operations.
   */
  ConsoleView() {
    initialiseView();
  }

  /**
   * Start the ConsoleView.
   *
   * @param args arguments
   * @throws IOException throw exception when user input undefined
   */
  public static void main(String[] args) throws IOException {

    System.out.println("PROGRAM START");
    View consoleView = new ConsoleView();
    String fileName = null;
    // Check if the filename argument is provided
    if (args.length > 0) {
      fileName = args[0];
      System.out.println(fileName);
    }

    if (fileName != null) {
      System.out.println(consoleView.output("run " + fileName));
    } else {
      String inputCommand = "";
      // Enter data using BufferReader
      BufferedReader reader = new BufferedReader(
              new InputStreamReader(System.in));

      while (!inputCommand.equals("exit")) {
        inputCommand = reader.readLine();
        System.out.println(consoleView.output(inputCommand));
      }
    }
  }
}
