import java.io.IOException;


/**
 * An abstract class that implements the View interface.
 * declares a Controller instance variable to interact with the underlying model
 */
public abstract class AbstractView implements View {
  protected Controller controller;

  @Override
  public void initialiseView() {
    controller = new ControllerImpl();
  }

  @Override
  public String output(String inputText) throws IOException {
    return controller.processCommand(inputText);
  }

}
