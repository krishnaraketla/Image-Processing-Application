import java.io.IOException;

/**
 * Controller for the image processing and enhancement application.
 * Manages underlying model to perform user required operations
 */
public interface Controller {

  /**
   * Calls underlying functions from model.
   */
  String processCommand(String inputText) throws IOException;

}
