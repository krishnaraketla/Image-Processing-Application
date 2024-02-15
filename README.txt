Overview of Design:

class/interface:

AbstractImage
An abstract class that defines the basic structure of an image. It provides methods to access and manipulate the pixels of an image, as well as get its width and height.

BMPImage
A class that represents a bitmap (BMP) image. It extends AbstractImage and provides methods to read and write BMP images from and to a file.

BMPImageFactory
A factory class that creates BMPImage objects from a given file path.

Image
An interface that defines the basic operations that can be performed on an image. It provides methods to get and set the pixel values of an image, as well as get its width and height.

ImageFactory
An interface for creating Image objects.

ImageType
An enum that represents the type of an image. It can be one of BMP, JPG, PNG, or PPM.

JPGImage
A class that represents a JPEG (JPG) image. It extends AbstractImage and provides methods to read and write JPG images from and to a file.

JPGImageFactory
A factory class that creates JPGImage objects from a given file path.

Pixel
A class that represents a single pixel in an image. It provides methods to get and set its color channels (red, green, blue, and alpha).

PNGImage
A class that represents a Portable Network Graphics (PNG) image. It extends AbstractImage and provides methods to read and write PNG images from and to a file.

PNGImageFactory
A factory class that creates PNGImage objects from a given file path.

PPMImage
A class that represents a Portable Pixmap (PPM) image. It extends AbstractImage and provides methods to read and write PPM images from and to a file.

PPMImageFactory
A factory class that creates PPMImage objects from a given file path.

AbstractModel
An abstract class that defines the basic structure of a model in a Model-View-Controller (MVC) architecture. It provides methods to register and unregister views, as well as notify them of changes in the model.

AbstractView
An abstract class that defines the basic structure of a view in a Model-View-Controller (MVC) architecture. It provides methods to register with a model and update its display based on changes in the model.

ConsoleView
A class that represents a view that displays the image on the console.

Controller
An interface that defines the basic operations that can be performed by a controller in a Model-View-Controller (MVC) architecture. It provides methods to handle user input and update the model accordingly.

ControllerImpl
A class that implements the Controller interface. It handles user input and updates the model and view accordingly.

GUIView
A class that represents a view that displays the image in a graphical user interface (GUI).

ImprovedModel
A class that implements the Model interface. It provides additional functionality to the model, such as image filtering and transformation.

Model
An interface that defines the basic operations that can be performed on a model in a Model-View-Controller (MVC) architecture. It provides methods to register and unregister views, as well as update the views based on changes in the model.

ModelImpl
A class that implements the Model interface. It provides basic functionality to the model, such as loading and saving images.

View
An interface that defines the basic operations that can be performed on a view in a Model-View-Controller (MVC) architecture. It provides methods to register with a model, update the display based on changes in the model, and handle user input.
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

Changes in Design:


1. Added a new interface EnhancedModel that extends the improvedModel to provide the histogram feature
2. Enhanced Model implementation extends the Abstract Model class. Objects changed to create object of this class in necessary places
3. GUIView class implementation of the View interface
4. AbstractController implementation of Controller interface
5. GUIController implementation extends the AbstractController
6. This class uses the same functionality of the old Controller, but has a few additional capabilities
 

-------------

RESOURCES

sample1.ppm - https://filesamples.com/samples/image/bmp/sample_640×426.bmp image using GIMP
