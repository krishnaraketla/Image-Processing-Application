import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import filter.BlurFilter;
import filter.GreyScaleFilter;
import filter.SepiaFilter;
import filter.SharpenFilter;

/**
 * Represents a controller implementation for managing the Image Model.
 */
public abstract class AbstractController implements Controller {
  protected EnchancedModel modelImpl;

  /**
   * Set model.
   *
   * @param modelImpl model
   */
  protected void setModelImpl(EnchancedModel modelImpl) {
    this.modelImpl = modelImpl;
  }

  @Override
  public String processCommand(String inputText) throws IOException {
    String result;
    String[] tokens = inputText.split(" ");
    String command = tokens[0];
    String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

    switch (command) {
      case "load":
        this.modelImpl.loadImage(args[0], args[1]);
        result = "Image Load Complete.";
        break;
      case "save":
        modelImpl.saveImage(args[0], args[1]);
        result = args[0];
        break;
      case "greyscale":
        //controller.toGreyscale(args[1], args[2], args[0]);
        if (args.length == 3) {
          String imageName = args[1];
          String destImageName = args[2];
          String valueComponent = args[0];
          switch (valueComponent) {
            case "red-component":
              modelImpl.greyscaleRedComponent(imageName, destImageName);
              break;
            case "blue-component":
              modelImpl.greyscaleBlueComponent(imageName, destImageName);
              break;
            case "green-component":
              modelImpl.greyscaleGreenComponent(imageName, destImageName);
              break;
            case "value-component":
              modelImpl.greyscaleValueComponent(imageName, destImageName);
              break;
            case "luma-component":
              modelImpl.greyscaleLumaComponent(imageName, destImageName);
              break;
            default:
              throw new IOException("Invalid valueComponent");
          }
        } else if (args.length == 2) {
          modelImpl.transform(new GreyScaleFilter(), args[0], args[1]);
        }
        result = "Image Greyscale Complete.";
        break;
      case "horizontal-flip":
        modelImpl.flipHorizontal(args[0], args[1]);
        result = "Image.Image Horizontal-flip Complete.";
        break;
      case "vertical-flip":
        modelImpl.flipVertical(args[0], args[1]);
        result = "Image.Image Vertical-flip Complete.";
        break;
      case "rgb-split":
        modelImpl.split(args[0], args[1], args[2], args[3]);
        result = "Image.Image rgb-split Complete.";
        break;
      case "rgb-combine":
        modelImpl.combine(args[0], args[1], args[2], args[3]);
        result = "Image.Image rgb-combine Complete.";
        break;
      case "brighten":
        int increment = Integer.parseInt(args[0]);
        modelImpl.brighten(increment, args[1], args[2]);
        result = "Image.Image brighten Complete.";
        break;
      case "darken":
        int value = Integer.parseInt(args[0]);
        modelImpl.darken(value, args[1], args[2]);
        result = "Image.Image darken Complete.";
        break;
      case "dither":
        modelImpl.dither(args[0], args[1]);
        result = "Image dither Complete.";
        break;
      case "blur":
        modelImpl.applyFilter(new BlurFilter(), args[0], args[1]);
        result = "Image blur Complete.";
        break;
      case "sharpen":
        modelImpl.applyFilter(new SharpenFilter(), args[0], args[1]);
        result = "Image blur Complete.";
        break;
      case "sepia":
        modelImpl.transform(new SepiaFilter(), args[0], args[1]);
        result = "sepia transform Complete.";
        break;
      case "run":
        String fileName = args[0];
        result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
          String line;
          while ((line = br.readLine()) != null) {
            result = result + processCommand(line) + "\n";
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case "exit":
        result = "Quitting app!";
        break;
      default:
        result = "Invalid command.";
    }
    return result;
  }
}
