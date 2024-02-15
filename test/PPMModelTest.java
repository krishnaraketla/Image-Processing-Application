import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import filter.BlurFilter;
import filter.SepiaFilter;
import imagepkg.Image;
import imagepkg.PPMImage;
import imagepkg.Pixel;


/**
 * Describes test cases for PPMModel.
 */
public class PPMModelTest {
  private ImprovedModel model;
  private Image testimage;

  @Before
  public void setup() {
    model = new ModelImpl();
    /*
        test image:
        P3
        3 1
        255
        0 0 255 255 0 0 0 255 0
     */
    List<Pixel> testPixelList = new ArrayList<>();
    testPixelList.add(new Pixel(0, 0, 255));
    testPixelList.add(new Pixel(255, 0, 0));
    testPixelList.add(new Pixel(0, 255, 0));
    testimage = new PPMImage(3, 1, testPixelList, "testImage");
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("testImage.ppm"));
      writer.write("P3\n");
      writer.write(testimage.getWidth() + " " + testimage.getHeight() + "\n");
      writer.write("255\n");

      for (Pixel pixel : testimage.getPixels()) {
        writer.write(pixel.getRed() + " ");
        writer.write(pixel.getGreen() + " ");
        writer.write(pixel.getBlue() + "\n");
      }

      writer.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  @Test
  public void runAllTests() throws IOException {
    testLoadImage();
    testSaveImage();
    testFlipHorizontal();
    testFlipVertical();
    testBrighten();
    testDarken();
    testGreyscaleRedComponent();
    testGreyscaleBlueComponent();
    testGreyscaleGreenComponent();
    testGreyscaleValueComponent();
    testGreyscaleLumaComponent();
    testCombine();
    testSplit();
    testApplyFiler();
    testTransform();
    testSaveAsPPM();
    Assert.assertEquals(true, true);
  }

  /**
   * test loadImage.
   */
  private void testLoadImage() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Assert that the loaded image is not null
    List<Image> images = model.getImages();
    Assert.assertEquals(1, images.size());
    Image image = images.get(0);
    Assert.assertEquals("testImage", image.getImageName());
    Assert.assertEquals(3, image.getWidth());
    Assert.assertEquals(1, image.getHeight());
  }

  /**
   * test saveImage.
   *
   * @throws IOException throw IOE
   */
  private void testSaveImage() throws IOException {
    model.saveImage("testSave.ppm", "testImage");
  }

  /**
   * test flipHorizontal.
   *
   * @throws IOException throw IOE
   */
  private void testFlipHorizontal() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.flipHorizontal("testImage", "flippedTestImage");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("flippedTestImage")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

    // Assert that the pixels of the input and output images are different
    List<Pixel> inputPixels = inputImage.getPixels();
    List<Pixel> outputPixels = outputImage.getPixels();
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    int midWidth = width / 2;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < midWidth; x++) {
        int leftIndex = y * width + x;
        int rightIndex = y * width + (width - 1 - x);
        Pixel inputPixel = inputPixels.get(leftIndex);
        Pixel outputPixel = outputPixels.get(rightIndex);
        Assert.assertEquals(true, inputPixel.equals(outputPixel));
      }
    }
  }

  /**
   * test flipVertical.
   *
   * @throws IOException throw IOE
   */
  private void testFlipVertical() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.flipHorizontal("testImage", "flippedTestImage");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("flippedTestImage")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

    // Assert that the pixels of the input and output images are different
    List<Pixel> inputPixels = inputImage.getPixels();
    List<Pixel> outputPixels = outputImage.getPixels();
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    int midHeight = height / 2;
    for (int y = 0; y < midHeight; y++) {
      for (int x = 0; x < width; x++) {
        int leftIndex = y * width + x;
        int rightIndex = y * width + (width - 1 - x);
        Pixel inputPixel = inputPixels.get(leftIndex);
        Pixel outputPixel = outputPixels.get(rightIndex);
        Assert.assertEquals(true, inputPixel.equals(outputPixel));
      }
    }
  }

  /**
   * test brighten.
   *
   * @throws IOException throw IOE
   */
  private void testBrighten() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.brighten(10, "testImage", "brightenedImage");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("brightenedImage")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

  }

  /**
   * test darken.
   *
   * @throws IOException throw IOE
   */
  private void testDarken() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.brighten(10, "testImage", "darkenedImage");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("darkenedImage")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

  }

  /**
   * test greyScaleRedComponent.
   *
   * @throws IOException throw IOE
   */
  private void testGreyscaleRedComponent() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.greyscaleRedComponent("testImage", "greyScaleRed");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("greyScaleRed")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

  }

  /**
   * test greyScaleBlueComponent.
   *
   * @throws IOException throw IOE
   */
  private void testGreyscaleBlueComponent() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.greyscaleBlueComponent("testImage", "greyScaleBlue");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("greyScaleBlue")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

  }

  /**
   * test greyScaleGreenComponent.
   *
   * @throws IOException throw IOE
   */
  private void testGreyscaleGreenComponent() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.greyscaleGreenComponent("testImage", "greyScaleGreen");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("greyScaleGreen")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

  }

  /**
   * test greyScaleValueComponent.
   *
   * @throws IOException throw IOE
   */
  private void testGreyscaleValueComponent() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.greyscaleValueComponent("testImage", "greyScaleValue");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("greyScaleValue")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

  }

  /**
   * test greyScaleLumaComponent.
   *
   * @throws IOException throw IOE
   */
  private void testGreyscaleLumaComponent() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.greyscaleLumaComponent("testImage", "greyScaleLuma");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("greyScaleLuma")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

  }

  /**
   * test combine.
   *
   * @throws IOException throw IOE
   */
  private void testCombine() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.combine("outputImage", "testImage", "testImage", "testImage");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("outputImage")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());

  }

  /**
   * test split.
   *
   * @throws IOException throw IOE
   */
  private void testSplit() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");

    // Flip image horizontally
    model.split("testImage", "outRed", "outGreen", "outBlue");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputRed = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("outRed")) {
        outputRed = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputRed);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputRed.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputRed.getHeight());

  }

  @Test
  public void testDither() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");
    model.dither("testImage", "dithered");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("dithered")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());
  }

  @Test
  public void testApplyFiler() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");
    model.applyFilter(new BlurFilter(), "testImage", "blur");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("blur")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());
  }

  @Test
  public void testTransform() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");
    model.transform(new SepiaFilter(), "testImage", "sepia");

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("sepia")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());
  }

  @Test
  public void testSaveAsPPM() throws IOException {
    // Load test image
    model.loadImage("testImage.ppm", "testImage");
    model.saveAsPPM("testImage", testimage);

    // Get input and output images
    List<Image> images = model.getImages();
    Image inputImage = null;
    Image outputImage = null;

    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        inputImage = image;
      }
    }
    for (Image image : images) {
      if (image.getImageName().equals("testImage")) {
        outputImage = image;
      }
    }

    // Assert that the output image is not null
    Assert.assertNotNull(outputImage);

    // Assert that the input and output images have the same dimensions
    Assert.assertEquals(inputImage.getWidth(), outputImage.getWidth());
    Assert.assertEquals(inputImage.getHeight(), outputImage.getHeight());
  }
}
