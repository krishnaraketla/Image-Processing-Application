import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import  javax.swing.ImageIcon;




/**
 * A view that displays the image and histogram in a GUI.
 */
public class GUIView implements View {

  private Controller controller;
  private JFrame frame;
  private JPanel contentPanel;

  private JPanel chartScrollPane;
  private JLabel imageLabel;
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private String currentImagePath;
  private String currentImageName;

  /**
   * Constructor for GUIView.
   * Initialise all private variables.
   *
   * @param controller controller
   */
  public GUIView(Controller controller) {
    this.controller = controller;
    frame = new JFrame();
    contentPanel = new JPanel(new BorderLayout());
    chartScrollPane = new JPanel();
    chartScrollPane.setPreferredSize(new Dimension(100, 100));
    imageLabel = new JLabel();
    menuBar = new JMenuBar();


    fileMenu = new JMenu();
    currentImagePath = "";
    currentImageName = "";
  }


  /**
   * Initialize the view.
   */

  @Override
  public void initialiseView() {
    frame = new JFrame("Image Processing and Enhancement");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    fileMenu = new JMenu("File");
    JMenuItem loadMenuItem = new JMenuItem("Load");
    JMenuItem exportMenuItem = new JMenuItem("Export");
    fileMenu.add(loadMenuItem);
    fileMenu.add(exportMenuItem);
    menuBar.add(fileMenu);
    frame.setJMenuBar(menuBar);

    JScrollPane scrollPane;

    JPanel buttonPanel = new JPanel(new GridLayout(18, 1, 5, 5));
    buttonPanel.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
            .getScreenSize().getWidth() * 0.1), 0));
    buttonPanel.setBackground(Color.GRAY);
    JButton flipButton = new JButton("Flip Horizontal");

    flipButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("horizontal-flip "
                + currentImageName + " " + currentImageName + "_fH");
        currentImageName = currentImageName + "_fH";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(flipButton, BorderLayout.NORTH);

    JButton flipVerticalButton = new JButton("Flip Vertical");
    flipVerticalButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("vertical-flip "
                + currentImageName + " " + currentImageName
                + "_fV");
        currentImageName = currentImageName + "_fV";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(flipVerticalButton, BorderLayout.NORTH);

    JPopupMenu filter = new JPopupMenu("Filter");
    JPopupMenu transform = new JPopupMenu("Transform");
    JMenuItem greyScaleDropDown = new JMenuItem("GreyScale");
    JMenuItem sepiaDropDown = new JMenuItem("Sepia");
    JMenuItem sharpenDropDown = new JMenuItem("Sharpen");
    JMenuItem blurDropDown = new JMenuItem("Blur");
    transform.add(greyScaleDropDown);
    transform.add(sepiaDropDown);
    filter.add(sharpenDropDown);
    filter.add(blurDropDown);

    JButton dropdownButton = new JButton("Filters");
    dropdownButton.addActionListener(e -> filter.show(dropdownButton, 0,
            dropdownButton.getHeight()));
    buttonPanel.add(dropdownButton);

    JButton transformButton = new JButton("Transform");
    transformButton.addActionListener(e -> transform.show(transformButton, 0,
            transformButton.getHeight()));
    buttonPanel.add(transformButton);

    blurDropDown.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("blur " + currentImageName
                + " " + currentImageName + "_b");
        currentImageName = currentImageName + "_b";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    sharpenDropDown.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("sharpen " + currentImageName
                + " " + currentImageName + "_s");
        currentImageName = currentImageName + "_s";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    JButton brightenButton = new JButton("Brighten");
    brightenButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("brighten 5 " + currentImageName
                + " " + currentImageName 
                + "_b5");
        currentImageName = currentImageName + "_b5";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(brightenButton, BorderLayout.NORTH);

    JButton darkenButton = new JButton("Darken");
    darkenButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("darken 5 " + currentImageName
                + " " + currentImageName 
                + "_d5");
        currentImageName = currentImageName + "_d5";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(darkenButton, BorderLayout.NORTH);

    greyScaleDropDown.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("greyscale " + currentImageName
                + " " + currentImageName + "_g");
        currentImageName = currentImageName + "_g";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    sepiaDropDown.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("sepia " + currentImageName
                + " " + currentImageName + "_s");
        currentImageName = currentImageName + "_s";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    JButton ditherButton = new JButton("Dither");
    ditherButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("dither " + currentImageName
                + " " + currentImageName + "_d");
        currentImageName = currentImageName + "_d";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(ditherButton, BorderLayout.NORTH);

    JButton greyscaleRedChannelButton = new JButton("Greyscale Red");
    greyscaleRedChannelButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("greyscale red-component "
                + currentImageName + " " + currentImageName + "_gR");
        currentImageName = currentImageName + "_gR";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(greyscaleRedChannelButton, BorderLayout.NORTH);

    JButton greyscaleGreenChannelButton = new JButton("Greyscale Green");
    greyscaleGreenChannelButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("greyscale green-component "
                + currentImageName + " " + currentImageName + "_gG");
        currentImageName = currentImageName + "_gG";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(greyscaleGreenChannelButton, BorderLayout.NORTH);

    JButton greyscaleBlueChannelButton = new JButton("Greyscale Blue");
    greyscaleBlueChannelButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("greyscale blue-component "
                + currentImageName + " " + currentImageName + "_gB");
        currentImageName = currentImageName + "_gB";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(greyscaleBlueChannelButton, BorderLayout.NORTH);

    JButton greyscaleValueChannelButton = new JButton("Greyscale Value");
    greyscaleValueChannelButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("greyscale value-component "
                + currentImageName + " " + currentImageName + "_gV");
        currentImageName = currentImageName + "_gV";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(greyscaleValueChannelButton, BorderLayout.NORTH);

    JButton greyscaleLuminosityChannelButton = new JButton("Greyscale Luma");
    greyscaleLuminosityChannelButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        controller.processCommand("greyscale luma-component "
                + currentImageName + " " + currentImageName + "_gL");
        currentImageName = currentImageName + "_gL";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(greyscaleLuminosityChannelButton, BorderLayout.NORTH);

    JButton redComponentButton = new JButton("Red Component");
    redComponentButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        //rgb-split intputImage image1 image2 image3
        controller.processCommand("rgb-split " + currentImageName
                + " " + currentImageName + "_rc" + " " + currentImageName
                + "_gc" + " " + currentImageName + "_bc");
        currentImageName = currentImageName + "_rc";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(redComponentButton, BorderLayout.NORTH);

    JButton greenComponentButton = new JButton("Green Component");
    greenComponentButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        //rgb-split intputImage image1 image2 image3
        controller.processCommand("rgb-split " + currentImageName
                + " " + currentImageName + "_rc" + " " + currentImageName
                + "_gc" + " " + currentImageName + "_bc");
        currentImageName = currentImageName + "_gc";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(greenComponentButton, BorderLayout.NORTH);

    JButton blueComponentButton = new JButton("Blue Component");
    blueComponentButton.addActionListener(e -> {
      ImageIcon imageIcon = new ImageIcon(currentImagePath);
      imageLabel.setIcon(imageIcon);
      try {
        //rgb-split intputImage image1 image2 image3
        controller.processCommand("rgb-split " + currentImageName + " "
                + currentImageName + "_rc" + " " + currentImageName
                + "_gc" + " " + currentImageName + "_bc");
        currentImageName = currentImageName + "_bc";
        currentImagePath = "temp/temp_" + currentImageName + ".png";
        ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
        imageLabel.setIcon(imageIcon1);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(blueComponentButton, BorderLayout.NORTH);

    imageLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon(currentImagePath);
    imageLabel.setIcon(imageIcon);

    loadMenuItem.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(frame);
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String filename = selectedFile.getAbsolutePath();
        String imagename = selectedFile.getName().split("\\.")[0];
        try {
          controller.processCommand("load " + filename + " " + imagename);
          currentImageName = imagename;
          currentImagePath = "temp/temp_" + imagename + ".png";
          ImageIcon imageIcon1 = new ImageIcon(currentImagePath);
          imageLabel.setIcon(imageIcon1);
          controller.processCommand("generateHistogram " + imagename);
          //scrollPane.add(createChart());

        } catch (IOException ex) {
          JOptionPane.showMessageDialog(frame, "Error loading image: " + ex.getMessage());
        }
      }
    });

    exportMenuItem.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showSaveDialog(frame);
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String filename = selectedFile.getAbsolutePath();
        try {
          controller.processCommand("save " + filename + " " + currentImageName);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(frame, "Error exporting image: " + ex.getMessage());
        }
      }
    });

    scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize()
            .getWidth() * 0.9), (int) (Toolkit.getDefaultToolkit().getScreenSize()
            .getHeight() * 0.9)));
    scrollPane.add(chartScrollPane);
    contentPanel.add(scrollPane, BorderLayout.CENTER);
    contentPanel.add(buttonPanel, BorderLayout.EAST);
    frame.setContentPane(contentPanel);
    frame.pack();
    frame.setVisible(true);
  }


  @Override
  public String output(String inputText) throws IOException {
    // Not used for GUIView
    return null;
  }

  /**
   * The main method for the GUI view.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException {

    System.out.println("PROGRAM START");
    Controller controller1 = new GUIController();
    new GUIView(controller1).initialiseView();
  }
}

