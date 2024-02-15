/**
 * Represents a controller implementation for managing the PPM Image Model.
 */
public class ControllerImpl extends AbstractController {
  /**
   * Default constructor to initialize the underlying model.
   */
  public ControllerImpl() {

    modelImpl = new ModelImpl();
    setModelImpl(modelImpl);
  }
}