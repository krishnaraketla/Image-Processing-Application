import java.io.IOException;

/**
 * Represents a View of the Image.Image enhancement and processing application.
 * Describes all actions a user can perform
 */
public interface View {

  /**
   * Intialise the View and Controller object.
   */
  void initialiseView();

  /**
   * Process any user operation done on the view.
   *
   * @param inputText user input operation
   * @return result string
   * @throws IOException when user input is not valid
   */
  String output(String inputText) throws IOException;

}
