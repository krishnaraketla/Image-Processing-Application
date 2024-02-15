import java.io.IOException;
import java.util.List;

import filter.ImageFilter;
import filter.ImageTransform;
import imagepkg.Image;

/**
 * Mock Model that implements ImprovedModel.
 */
public class MockModel implements EnchancedModel {

  private boolean loadImageCalled;
  private boolean saveImageCalled;
  private boolean transformCalled;
  private boolean flipHorizontalCalled;
  private boolean flipVerticalCalled;
  private boolean splitCalled;
  private boolean combineCalled;
  private boolean brightenCalled;
  private boolean darkenCalled;
  private boolean ditherCalled;
  private boolean applyFilterCalled;
  private boolean greyscaleRedComponentCalled;
  private boolean greyscaleBlueComponentCalled;
  private boolean greyscaleGreenComponentCalled;
  private boolean greyscaleValueComponentCalled;
  private boolean greyscaleLumaComponentCalled;
  private boolean saveAsPPMCalled;
  private boolean saveAsBMPCalled;
  private boolean saveAsJPGCalled;
  private boolean saveAsPNGCalled;

  public boolean isLoadImageCalled() {
    return loadImageCalled;
  }

  public boolean isSaveImageCalled() {
    return saveImageCalled;
  }

  public boolean isTransformCalled() {
    return transformCalled;
  }

  public boolean isFlipHorizontalCalled() {
    return flipHorizontalCalled;
  }

  public boolean isFlipVerticalCalled() {
    return flipVerticalCalled;
  }

  public boolean isSplitCalled() {
    return splitCalled;
  }

  public boolean isCombineCalled() {
    return combineCalled;
  }

  public boolean isBrightenCalled() {
    return brightenCalled;
  }

  public boolean isDarkenCalled() {
    return darkenCalled;
  }

  public boolean isDitherCalled() {
    return ditherCalled;
  }

  public boolean isApplyFilterCalled() {
    return applyFilterCalled;
  }

  public boolean isGreyscaleRedComponentCalled() {
    return greyscaleRedComponentCalled;
  }

  public boolean isGreyscaleBlueComponentCalled() {
    return greyscaleBlueComponentCalled;
  }

  public boolean isGreyscaleGreenComponentCalled() {
    return greyscaleGreenComponentCalled;
  }

  public boolean isGreyscaleValueComponentCalled() {
    return greyscaleValueComponentCalled;
  }

  public boolean isGreyscaleLumaComponentCalled() {
    return greyscaleLumaComponentCalled;
  }

  public boolean isSaveAsPPMCalled() {
    return saveAsPPMCalled;
  }

  public boolean isSaveAsBMPCalled() {
    return saveAsBMPCalled;
  }

  public boolean isSaveAsJPGCalled() {
    return saveAsJPGCalled;
  }

  public boolean isSaveAsPNGCalled() {
    return saveAsPNGCalled;
  }

  /**
   * Constructor for MockModel.
   */
  MockModel() {
    // initialise private variables
    this.loadImageCalled = false;
    this.saveImageCalled = false;
    this.transformCalled = false;
    this.flipHorizontalCalled = false;
    this.flipVerticalCalled = false;
    this.splitCalled = false;
    this.combineCalled = false;
    this.brightenCalled = false;
    this.darkenCalled = false;
    this.ditherCalled = false;
    this.applyFilterCalled = false;
    this.greyscaleRedComponentCalled = false;
    this.greyscaleBlueComponentCalled = false;
    this.greyscaleGreenComponentCalled = false;
    this.greyscaleValueComponentCalled = false;
    this.greyscaleLumaComponentCalled = false;


  }

  @Override
  public void loadImage(String filename, String imageName) {
    loadImageCalled = true;
  }

  @Override
  public void saveImage(String fileName, String imageName) throws IOException {
    saveImageCalled = true;
  }

  @Override
  public void flipHorizontal(String imageName, String outputImageName) throws IOException {
    flipHorizontalCalled = true;
  }

  @Override
  public void flipVertical(String imageName, String outputImageName) throws IOException {
    flipVerticalCalled = true;
  }

  @Override
  public void brighten(int value, String imageName, String outputImageName) throws IOException {
    brightenCalled = true;
  }

  @Override
  public void darken(int value, String imageName, String outputImageName) throws IOException {
    darkenCalled = true;
  }

  @Override
  public void greyscaleRedComponent(String imageName, String outputImageName) throws IOException {
    greyscaleRedComponentCalled = true;
  }

  @Override
  public void greyscaleBlueComponent(String imageName, String outputImageName) throws IOException {
    greyscaleBlueComponentCalled = true;
  }

  @Override
  public void greyscaleGreenComponent(String imageName, String outputImageName) throws IOException {
    greyscaleGreenComponentCalled = true;
  }

  @Override
  public void greyscaleValueComponent(String imageName, String outputImageName) throws IOException {
    greyscaleValueComponentCalled = true;
  }

  @Override
  public void greyscaleLumaComponent(String imageName, String outputImageName) throws IOException {
    greyscaleLumaComponentCalled = true;
  }

  @Override
  public void combine(String destImageName, String image1, String image2, String image3)
          throws IOException {
    combineCalled = true;
  }

  @Override
  public void split(String imageName, String imageRed, String imageGreen, String imageBlue)
          throws IOException {
    splitCalled = true;
  }

  @Override
  public List<Image> getImages() {
    return null;
  }

  @Override
  public void applyFilter(ImageFilter filter, String imageName, String outputImageName)
          throws IOException {
    applyFilterCalled = true;
  }

  @Override
  public void transform(ImageTransform filter, String imageName, String outputImage)
          throws IOException {
    transformCalled = true;
  }

  @Override
  public void dither(String imputImageNme, String outputImageNames) {
    ditherCalled = true;
  }

  @Override
  public void saveAsPPM(String filename, Image imageName) throws IOException {
    saveAsPPMCalled = true;
  }

  @Override
  public void saveAsJPG(String filename, Image imageName) throws IOException {
    saveAsJPGCalled = true;
  }

  @Override
  public void saveAsBMP(String filename, Image imageName) throws IOException {
    saveAsBMPCalled = true;
  }

  @Override
  public void saveAsPNG(String filename, Image image) throws IOException {
    saveAsPNGCalled = true;
  }

  @Override
  public void generateHistogram(String imageName) throws IOException {
    // TODO Auto-generated method stub
  }
}
