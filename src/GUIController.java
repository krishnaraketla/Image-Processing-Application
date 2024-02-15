import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a controller implementation for managing the PPM Image Model.
 */
public class GUIController extends AbstractController {

  /**
   * Default constructor to initialize the underlying model.
   */
  public GUIController() {

    modelImpl = new ModelImpl();
    setModelImpl(modelImpl);
  }

  /**
   * Set model.
   *
   * @param modelImpl model
   */
  public void setModelImpl(EnchancedModel modelImpl) {
    this.modelImpl = modelImpl;
  }

  @Override
  public String processCommand(String inputText) throws IOException {
    String tempImageName = "";
    String[] tokens = inputText.split(" ");
    String command = tokens[0];
    String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
    switch (command) {
      case "load":
        clearTempFolder();
        tempImageName = args[1];
        break;
      case "save":
        clearTempFolder();
        tempImageName = args[1];
        break;
      case "greyscale":
        clearTempFolder();
        if (args.length == 3) {
          tempImageName = args[2];
        } else if (args.length == 2) {
          tempImageName = args[1];
        }
        break;
      case "horizontal-flip":
        clearTempFolder();
        tempImageName = args[1];
        break;
      case "vertical-flip":
        clearTempFolder();
        tempImageName = args[1];
        break;
      case "blur":
        clearTempFolder();
        tempImageName = args[1];
        break;
      case "sharpen":
        clearTempFolder();
        tempImageName = args[1];
        break;
      case "sepia":
        clearTempFolder();
        tempImageName = args[1];
        break;
      case "dither":
        clearTempFolder();
        tempImageName = args[1];
        break;
      case "brighten":
        clearTempFolder();
        tempImageName = args[2];
        break;
      case "darken":
        clearTempFolder();
        tempImageName = args[2];
        break;
      case "rgb-split":
        clearTempFolder();
        modelImpl.split(args[0], args[1], args[2], args[3]);
        String result = "Image.Image rgb-split Complete.";
        modelImpl.saveImage("temp/temp_" + args[1] + ".png", args[1]);
        modelImpl.saveImage("temp/temp_" + args[2] + ".png", args[2]);
        modelImpl.saveImage("temp/temp_" + args[3] + ".png", args[3]);
        return result;
      case "generateHistogram":
        modelImpl.generateHistogram(args[0]);
        result = "generateHistogram Complete.";
        return result;
      default:
        throw new IOException("Invalid command");

    }
    String result = super.processCommand(inputText);
    modelImpl.saveImage("temp/temp_" + tempImageName + ".png", tempImageName);
    return result;
  }


  /**
   * Clear the temp folder.
   */
  private void clearTempFolder() {
    // if temp directory does not exist, create it
    File tempFolder = new File("temp");
    if (!tempFolder.exists()) {
      tempFolder.mkdir();
    }

    // delete all files in temp folder
    File[] files = tempFolder.listFiles();
    if (files != null) {
      for (File file : files) {
        file.delete();
      }
    }
  }
}