import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filter.ImageFilter;
import filter.ImageTransform;
import imagepkg.BMPImage;
import imagepkg.BMPImageFactory;
import imagepkg.Image;
import imagepkg.ImageFactory;
import imagepkg.ImageType;
import imagepkg.JPGImage;
import imagepkg.JPGImageFactory;
import imagepkg.PNGImage;
import imagepkg.PNGImageFactory;
import imagepkg.PPMImage;
import imagepkg.PPMImageFactory;
import imagepkg.Pixel;

/**
 * Represents an abstract implementation of Model.
 */
public abstract class AbstractModel implements EnchancedModel {
  protected List<Image> images;
  Map<ImageType, ImageFactory> imageFactories;
  Map<String, ImageType> imageTypeDictionary;

  /**
   * Default constructor that initialises list of images that are maintained during session.
   */
  AbstractModel() {
    imageFactories = new HashMap<>();
    imageFactories.put(ImageType.PPM, new PPMImageFactory());
    imageFactories.put(ImageType.JPG, new JPGImageFactory());
    imageFactories.put(ImageType.BMP, new BMPImageFactory());
    imageFactories.put(ImageType.PNG, new PNGImageFactory());

    imageTypeDictionary = new HashMap<>();
    imageTypeDictionary.put("ppm", ImageType.PPM);
    imageTypeDictionary.put("bmp", ImageType.BMP);
    imageTypeDictionary.put("jpg", ImageType.JPG);
    imageTypeDictionary.put("png", ImageType.JPG);

    images = new ArrayList<>();
  }

  @Override
  public void loadImage(String filename, String imageName) throws IOException {
    filename = filename.toLowerCase();
    int extensionIndex = filename.lastIndexOf(".");
    String extension;
    if (extensionIndex == -1) {
      throw new IOException("No file extension found.");
    } else {
      extension = filename.substring(extensionIndex + 1);
    }
    if (imageTypeDictionary.get(extension) == ImageType.BMP) {
      images.add(new BMPImage(filename, imageName));
    } else if (imageTypeDictionary.get(extension) == ImageType.PPM) {
      images.add(new PPMImage(filename, imageName));
    } else if (imageTypeDictionary.get(extension) == ImageType.JPG) {
      images.add(new JPGImage(filename, imageName));
    } else if (imageTypeDictionary.get(extension) == ImageType.PNG) {
      images.add(new PNGImage(filename, imageName));
    }
  }

  @Override
  public void saveImage(String fileName, String imageName) throws IOException {
    fileName = fileName.toLowerCase();
    int extensionIndex = fileName.lastIndexOf(".");
    String extension;
    if (extensionIndex == -1) {
      throw new IOException("No file extension found.");
    } else {
      extension = fileName.substring(extensionIndex + 1);
    }

    Image image = getImage(imageName);
    if (image == null) {
      System.out.println("Invalid image: " + imageName);
      throw new IOException("Invalid image: " + imageName);
    }

    if (imageTypeDictionary.get(extension) == ImageType.BMP) {
      saveAsBMP(fileName, image);
    } else if (imageTypeDictionary.get(extension) == ImageType.PPM) {
      saveAsPPM(fileName, image);
    } else if (imageTypeDictionary.get(extension) == ImageType.JPG) {
      saveAsJPG(fileName, image);
    } else if (imageTypeDictionary.get(extension) == ImageType.PNG) {
      saveAsPNG(fileName, image);
    } else {
      throw new IOException("Invalid extension!");
    }
  }

  @Override
  public void flipHorizontal(String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }

    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> pixelList = new ArrayList<>(image.getPixels());

    int midWidth = width / 2;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < midWidth; x++) {
        int leftIndex = y * width + x;
        int rightIndex = y * width + (width - 1 - x);
        Pixel temp = pixelList.get(leftIndex);
        pixelList.set(leftIndex, pixelList.get(rightIndex));
        pixelList.set(rightIndex, temp);
      }
    }
    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, pixelList, outputImageName));
  }

  @Override
  public void flipVertical(String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> pixelList = new ArrayList<>(image.getPixels());

    int midHeight = height / 2;
    for (int y = 0; y < midHeight; y++) {
      for (int x = 0; x < width; x++) {
        int topIndex = y * width + x;
        int bottomIndex = (height - 1 - y) * width + x;
        Pixel temp = pixelList.get(topIndex);
        pixelList.set(topIndex, pixelList.get(bottomIndex));
        pixelList.set(bottomIndex, temp);
      }
    }
    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, pixelList, outputImageName));
  }

  @Override
  public void brighten(int value, String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> newPixelList = new ArrayList<>();

    for (Pixel pixel : image.getPixels()) {

      int red = Math.min(255, pixel.getRed() + value);
      int green = Math.min(255, pixel.getGreen() + value);
      int blue = Math.min(255, pixel.getBlue() + value);

      newPixelList.add(new Pixel(red, green, blue));
    }

    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, newPixelList, outputImageName));
  }

  @Override
  public void darken(int value, String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> newPixelList = new ArrayList<>();

    double gamma = 0.9;

    for (Pixel pixel : image.getPixels()) {
      int red = pixel.getRed();
      int green = pixel.getGreen();
      int blue = pixel.getBlue();

      red = (int) Math.min(255, Math.pow(red, gamma) + value);
      green = (int) Math.min(255, Math.pow(green, gamma) + value);
      blue = (int) Math.min(255, Math.pow(blue, gamma) + value);

      newPixelList.add(new Pixel(red, green, blue));
    }

    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, newPixelList, outputImageName));
  }

  @Override
  public void greyscaleRedComponent(String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> newPixelList = new ArrayList<>();

    for (Pixel pixel : image.getPixels()) {
      int red = pixel.getRed();
      int green = 0;
      int blue = 0;

      // Calculate the average grayscale value of the pixel
      int gray = Math.round((red + green + blue) / 3);

      newPixelList.add(new Pixel(gray, gray, gray));
    }

    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, newPixelList, outputImageName));
  }

  @Override
  public void greyscaleBlueComponent(String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> newPixelList = new ArrayList<>();

    for (Pixel pixel : image.getPixels()) {
      int red = 0;
      int green = 0;
      int blue = pixel.getBlue();

      // Calculate the average grayscale value of the pixel
      int gray = Math.round((red + green + blue) / 3);

      newPixelList.add(new Pixel(gray, gray, gray));
    }

    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, newPixelList, outputImageName));
  }

  @Override
  public void greyscaleGreenComponent(String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> newPixelList = new ArrayList<>();

    for (Pixel pixel : image.getPixels()) {
      int red = 0;
      int green = pixel.getGreen();
      int blue = 0;

      // Calculate the average grayscale value of the pixel
      int gray = Math.round((red + green + blue) / 3);

      newPixelList.add(new Pixel(gray, gray, gray));
    }

    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, newPixelList, outputImageName));
  }

  @Override
  public void greyscaleValueComponent(String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> newPixelList = new ArrayList<>();

    for (Pixel pixel : image.getPixels()) {

      // Calculate the average grayscale value of the pixel
      int gray = (int) Math.round(pixel.getValue());

      newPixelList.add(new Pixel(gray, gray, gray));
    }

    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, newPixelList, outputImageName));
  }

  @Override
  public void greyscaleLumaComponent(String imageName, String outputImageName) throws IOException {
    Image image = getImage(imageName);
    if (image == null) {
      throw new IOException("Invalid image");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    List<Pixel> newPixelList = new ArrayList<>();

    for (Pixel pixel : image.getPixels()) {

      // Calculate the average grayscale value of the pixel
      int gray = (int) Math.round(pixel.getLuma());

      newPixelList.add(new Pixel(gray, gray, gray));
    }

    images.add(imageFactories.get(image.getImageType())
            .createImage(width, height, newPixelList, outputImageName));
  }

  @Override
  public void combine(String desImage, String image1, String image2, String image3)
          throws IOException {
    Image inputImage1 = getImage(image1);
    Image inputImage2 = getImage(image2);
    Image inputImage3 = getImage(image3);
    if (inputImage1 == null || inputImage2 == null || inputImage3 == null) {
      throw new IOException("Invalid image");
    }
    int width = inputImage1.getWidth();
    int height = inputImage1.getHeight();

    // Ensure that all images have the same dimensions
    if (inputImage2.getWidth() != width || inputImage2.getHeight() != height
            || inputImage3.getWidth() != width || inputImage3.getHeight() != height) {
      throw new IOException("Input images must have the same dimensions.");
    }

    List<Pixel> newPixelList = new ArrayList<>();

    for (int i = 0; i < inputImage1.getPixels().size(); i++) {
      Pixel pixel1 = inputImage1.getPixels().get(i);
      Pixel pixel2 = inputImage2.getPixels().get(i);
      Pixel pixel3 = inputImage3.getPixels().get(i);

      // Compute the averages of the RGB components
      int red = (pixel1.getRed() + pixel2.getRed() + pixel3.getRed()) / 3;
      int green = (pixel1.getGreen() + pixel2.getGreen() + pixel3.getGreen()) / 3;
      int blue = (pixel1.getBlue() + pixel2.getBlue() + pixel3.getBlue()) / 3;

      newPixelList.add(new Pixel(red, green, blue));
    }
    images.add(imageFactories.get(inputImage1.getImageType())
            .createImage(width, height, newPixelList, desImage));
  }

  @Override
  public void split(String imageName, String imageRed, String imageGreen, String imageBlue)
          throws IOException {
    Image source = getImage(imageName);
    if (source == null) {
      throw new IOException("Invalid image");
    }
    int width = source.getWidth();
    int height = source.getHeight();
    List<Pixel> pixelList = source.getPixels();

    List<Pixel> redPixels = new ArrayList<>();
    List<Pixel> greenPixels = new ArrayList<>();
    List<Pixel> bluePixels = new ArrayList<>();

    // Split the pixels into three lists based on their RGB components
    for (Pixel pixel : pixelList) {
      redPixels.add(new Pixel(pixel.getRed(), 0, 0));
      greenPixels.add(new Pixel(0, pixel.getGreen(), 0));
      bluePixels.add(new Pixel(0, 0, pixel.getBlue()));
    }

    // Create and return three new images from the three lists of pixels
    Image redImage = imageFactories.get(source.getImageType())
            .createImage(width, height, redPixels, imageRed);
    Image greenImage = imageFactories.get(source.getImageType())
            .createImage(width, height, greenPixels, imageGreen);
    Image blueImage = imageFactories.get(source.getImageType())
            .createImage(width, height, bluePixels, imageBlue);

    images.add(redImage);
    images.add(greenImage);
    images.add(blueImage);

  }

  public List<Image> getImages() {
    return images;
  }

  @Override
  public void applyFilter(ImageFilter filter, String imageName, String outputImageName)
          throws IOException {
    Image source = getImage(imageName);
    if (source == null) {
      throw new IOException("Invalid image");
    }
    List<Pixel> outputPixels = filter.apply(source);
    images.add(imageFactories.get(source.getImageType())
            .createImage(source.getWidth(), source.getHeight(), outputPixels, outputImageName));
  }

  /**
   * Fetch the Image.Image object that corresponds to the imageName or return null if not found.
   *
   * @param imageName search term
   * @return Image.Image object or null
   */
  protected Image getImage(String imageName) {
    for (Image image : images) {
      if (image.getImageName().equals(imageName)) {
        return image;
      }
    }
    return null;
  }

  @Override
  public void saveAsPPM(String filename, Image imageName) throws IOException {
    Image newImage = new PPMImage(imageName.getWidth(), imageName.getHeight(),
            imageName.getPixels(), imageName.getImageName());
    newImage.saveImage(filename);
  }

  @Override
  public void saveAsJPG(String filename, Image imageName) throws IOException {
    Image newImage = new JPGImage(imageName.getWidth(), imageName.getHeight(),
            imageName.getPixels(), imageName.getImageName());
    newImage.saveImage(filename);
  }

  @Override
  public void saveAsBMP(String filename, Image imageName) throws IOException {

    Image newImage = new BMPImage(imageName.getWidth(), imageName.getHeight(),
            imageName.getPixels(), imageName.getImageName());
    newImage.saveImage(filename);
  }

  @Override
  public void saveAsPNG(String filename, Image imageName) throws IOException {
    Image newImage = new PNGImage(imageName.getWidth(), imageName.getHeight(),
            imageName.getPixels(), imageName.getImageName());
    newImage.saveImage(filename);
  }

  @Override
  public void transform(ImageTransform filter, String imageName, String outputImage)
          throws IOException {
    Image source = getImage(imageName);
    if (source == null) {
      throw new IOException("Invalid image");
    }
    List<Pixel> outputPixels = filter.apply(source);
    images.add(imageFactories.get(source.getImageType())
            .createImage(source.getWidth(), source.getHeight(), outputPixels, outputImage));
  }

  @Override
  public void dither(String imageName, String outputImageName) throws IOException {
    Image source = getImage(imageName);
    if (source == null) {
      throw new IOException("Invalid image");
    }
    int width = source.getWidth();
    int height = source.getHeight();
    List<Pixel> pixels = source.getPixels();

    // Convert the image to grayscale
    List<Pixel> grayscalePixels = new ArrayList<>();
    for (Pixel pixel : pixels) {
      int grayValue = (int) pixel.getIntensity();
      grayscalePixels.add(new Pixel(grayValue, grayValue, grayValue));
    }

    // Apply the Steinberg dithering algorithm
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        Pixel oldPixel = grayscalePixels.get(r * width + c);
        int oldColor = oldPixel.getRed(); // We're using the red component for simplicity
        int newColor = (oldColor < 128) ? 0 : 255;
        int error = oldColor - newColor;
        Pixel newPixel = new Pixel(newColor, newColor, newColor);
        pixels.set(r * width + c, newPixel);

        if (c < width - 1) { // Pixel to the right
          int idx = r * width + (c + 1);
          Pixel rightPixel = grayscalePixels.get(idx);
          int rightColor = rightPixel.getRed();
          int newRightColor = Math.min(Math.max(rightColor + (int) (7.0 / 16 * error), 0), 255);
          Pixel newRightPixel = new Pixel(newRightColor, newRightColor, newRightColor);
          grayscalePixels.set(idx, newRightPixel);
        }
        if (r < height - 1) { // Next-row pixels
          if (c > 0) { // Next-row-left pixel
            int idx = (r + 1) * width + (c - 1);
            Pixel bottomLeftPixel = grayscalePixels.get(idx);
            int bottomLeftColor = bottomLeftPixel.getRed();
            int newBottomLeftColor = Math.min(Math.max(bottomLeftColor
                    + (int) (3.0 / 16 * error), 0), 255);
            Pixel newBottomLeftPixel =
                    new Pixel(newBottomLeftColor, newBottomLeftColor, newBottomLeftColor);
            grayscalePixels.set(idx, newBottomLeftPixel);
          }
          { // Next-row-middle pixel
            int idx = (r + 1) * width + c;
            Pixel bottomPixel = grayscalePixels.get(idx);
            int bottomColor = bottomPixel.getRed();
            int newBottomColor = Math.min(Math.max(bottomColor + (int) (5.0 / 16 * error), 0), 255);
            Pixel newBottomPixel = new Pixel(newBottomColor, newBottomColor, newBottomColor);
            grayscalePixels.set(idx, newBottomPixel);
          }
          if (c < width - 1) { // Next-row-right pixel
            int idx = (r + 1) * width + (c + 1);
            Pixel bottomRightPixel = grayscalePixels.get(idx);
            int bottomRightColor = bottomRightPixel.getRed();
            int newBottomRightColor = Math.min(Math.max(bottomRightColor
                    + (int) (1.0 / 16 * error), 0), 255);
            Pixel newBottomRightPixel =
                    new Pixel(newBottomRightColor, newBottomRightColor, newBottomRightColor);
            grayscalePixels.set(idx, newBottomRightPixel);
          }
        }
      }
    }

    // Create a new image with the dithered pixels
    Image ditheredImage = imageFactories.get(source.getImageType())
            .createImage(width, height, pixels, outputImageName);
    images.add(ditheredImage);
  }
}
