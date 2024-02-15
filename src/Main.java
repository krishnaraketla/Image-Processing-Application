import java.io.IOException;
import java.util.Arrays;

/**
 * Starts the program.
 */
public class Main {
  /**
   * Starts the program.
   *
   * @param args command line arguments
   * @throws IOException if there is an error reading the image file
   */
  public static void main(String[] args) throws IOException {
    if (args.length > 0 && args[0].equals("-file")) {
      System.out.println("File mode");
      // Create a ConsoleView object for file mode
      ConsoleView consoleView = new ConsoleView();
      consoleView.initialiseView();
      consoleView.output("run " + args[1]);
      // remove first element from args and pass that
      String[] consoleArgs = Arrays.copyOfRange(args, 1, args.length);
      consoleView.main(consoleArgs);
    } else if (args.length > 0 && args[0].equals("-text")) {
      System.out.println("Text mode");
      ConsoleView.main(new String[]{});
    } else {
      System.out.println("GUI mode");
      // Create a GUIView object for GUI mode
      Controller controller = new GUIController();
      GUIView guiView = new GUIView(controller);
      guiView.initialiseView();
    }
  }

}
