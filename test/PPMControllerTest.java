import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertThrows;

/**
 * Describes test for PPMController.
 */
public class PPMControllerTest {
  private ControllerImpl controller = new ControllerImpl();
  private MockModel mockModel = new MockModel();

  @Before
  public void setup() {
    controller.setModelImpl(mockModel);
  }

  @Test
  public void testProcessCommand() throws IOException {
    controller.processCommand("load testImage.ppm testImage");
    Assert.assertEquals(true, mockModel.isLoadImageCalled());
    controller.processCommand("save outputImage.ppm outputImage");
    Assert.assertEquals(true, mockModel.isSaveImageCalled());
    controller.processCommand("brighten 10 testImage outputImage");
    Assert.assertEquals(true, mockModel.isBrightenCalled());
    controller.processCommand("darken 10 testImage outputImage");
    Assert.assertEquals(true, mockModel.isDarkenCalled());
    controller.processCommand("greyscale red-component sample sample-greyscaleRed");
    Assert.assertEquals(true, mockModel.isGreyscaleRedComponentCalled());
    controller.processCommand("greyscale blue-component sample sample-greyscaleRed");
    Assert.assertEquals(true, mockModel.isGreyscaleBlueComponentCalled());
    controller.processCommand("greyscale green-component sample sample-greyscaleRed");
    Assert.assertEquals(true, mockModel.isGreyscaleGreenComponentCalled());
    controller.processCommand("greyscale value-component sample sample-greyscaleRed");
    Assert.assertEquals(true, mockModel.isGreyscaleValueComponentCalled());
    controller.processCommand("greyscale luma-component sample sample-greyscaleRed");
    Assert.assertEquals(true, mockModel.isGreyscaleLumaComponentCalled());

    assertThrows(IOException.class, () -> {
      controller.processCommand("greyscale invalid-argument sample sample-greyscaleRed");
    });

    controller.processCommand("horizontal-flip sample sample-horizontal");
    Assert.assertEquals(true, mockModel.isFlipHorizontalCalled());
    controller.processCommand("vertical-flip sample sample-vertical");
    Assert.assertEquals(true, mockModel.isFlipVerticalCalled());
    controller.processCommand("rgb-split intputImage image1 image2 image3");
    Assert.assertEquals(true, mockModel.isSplitCalled());
    controller.processCommand("rgb-combine outputImage image1 image2 image3");
    Assert.assertEquals(true, mockModel.isCombineCalled());
  }

  @Test
  public void testProcessCommandInvalidCommand() throws IOException {
    Assert.assertEquals("Invalid command.", controller.processCommand("invalid-command"));
  }
}
