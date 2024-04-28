/**
 * The Controller class is responsible for managing the interactions between the model and the view in
 * a Java application.
 */
package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import command.*;
import memento.Caretaker;
import modele.IPerspective;
import modele.Image;
import modele.OtherPerspective;
import modele.Perspective;
import observer.Observer;
import view.ParentPanel;
import view.UserView;

public class Controller {

    private UserView view;
    private Image modelImage;
    private Perspective modelPerspective;
    private OtherPerspective modelOtherPerspective;
    private Point lastPoint;
    private Point otherLastPoint;
    private int x;
    private int y;
    private Caretaker caretaker;
    private Serialization serialization = new Serialization();
    private String filePath;
    private static final String FILE_PATH_KEY = "filePath";
    private Preferences prefs = Preferences.userNodeForPackage(Controller.class);

    // The above code is defining a constructor for a Controller class in Java. The
    // constructor takes
    // in four parameters: modelImage, modelPerspective, modelOtherPerspective, and
    // view. It assigns
    // these parameters to the corresponding instance variables in the Controller
    // class. It also
    // initializes a caretaker object using the Caretaker.getInstance() method and
    // calls the
    // initController() method.
    // The above code is a constructor for a Java class called "Controller". It
    // takes in several
    // parameters: "modelImage", "modelPerspective", "modelOtherPerspective", and
    // "view". It
    // initializes the instance variables "modelImage", "modelPerspective",
    // "modelOtherPerspective",
    // and "view" with the corresponding parameter values. It also initializes the
    // instance variable
    // "caretaker" with an instance of the "Caretaker" class using the
    // "getInstance()" method. The
    // constructor also initializes the instance variable "filePath" with the value
    // retrieved from the
    // "prefs" object using the "
    public Controller(Image modelImage, Perspective modelPerspective, OtherPerspective modelOtherPerspective,
            UserView view) {
        this.modelImage = modelImage;
        this.modelPerspective = modelPerspective;
        this.modelOtherPerspective = modelOtherPerspective;
        this.view = view;
        caretaker = Caretaker.getInstance();
        this.filePath = prefs.get(FILE_PATH_KEY, "");
        initController();
    }

    /**
     * The `initController` function initializes the controllers for the three views
     * in the
     * application, sets up event listeners for various user interactions, and binds
     * the views to their
     * respective models.
     */
    private void initController() {

        // The above code is creating three observer objects: observerImage,
        // observerPerspective, and
        // observerOtherPerspective. These observer objects are obtained from the view
        // object using the
        // methods getVue1(), getVue2(), and getVue3().
        Observer observerImage = (Observer) this.view.getVue1();
        Observer observerPerspective = (Observer) this.view.getVue2();
        Observer observerOtherPerspective = (Observer) this.view.getVue3();

        // The above code is attaching observers to different models. It is attaching
        // the observer
        // "observerImage" to the model "modelImage", attaching the observer
        // "observerPerspective" to
        // the model "modelPerspective", and attaching the observer
        // "observerOtherPerspective" to the
        // model "modelOtherPerspective".
        this.modelImage.attach(observerImage);
        this.modelPerspective.attach(observerPerspective);
        this.modelOtherPerspective.attach(observerOtherPerspective);

        // The above code is adding an ActionListener to the "Charge" menu item in a
        // menu bar. When the
        // "Charge" menu item is clicked, the actionPerformed method will be called,
        // which in turn
        // calls the loadImage() method.
        this.view.getMenuBarBuilder().getMenuItemCharge().addActionListener(new ActionListener() {

            /**
             * This function is an event handler that calls the loadImage() method when an
             * action event
             * occurs.
             * 
             * @param e The parameter "e" is an instance of the ActionEvent class. It
             *          represents the
             *          event that triggered the actionPerformed method.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage();
            }
        });

        this.view.getMenuBarBuilder().getMenuItemShortcut().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            
                JOptionPane.showMessageDialog(null, "UNDO : CTRL + Z" + '\n' + "REDO : CTRL + Y " + '\n'
                        + "ROTATE : CTRL + R " + '\n' + "GLITCH : CTRL + G" + '\n' + "FILTRE NOIR : CTRL + N");
            }

        });

        // The above code is adding an ActionListener to the "Save" menu item in a menu
        // bar. When the
        // "Save" menu item is clicked, the actionPerformed method will be called, which
        // in turn calls
        // the saveFile() method.
        this.view.getMenuBarBuilder().getMenuItemSave().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        // The above code is adding a MouseWheelListener to the image label of the Vue2
        // object in the
        // view. When the mouse wheel is moved, the logZoom method is called with the
        // MouseWheelEvent
        // and modelPerspective as parameters.
        this.view.getVue2().getImageLabel().addMouseWheelListener(new MouseWheelListener() {
            /**
             * The function logs the zoom event and updates the model's perspective.
             * 
             * @param e The parameter "e" is a MouseWheelEvent object that represents the
             *          mouse wheel
             *          movement event. It contains information about the event, such as the
             *          amount and
             *          direction of the wheel movement.
             */
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                logZoom(e, modelPerspective);
            }

        });

        // The above code is adding a MouseWheelListener to the "Vue3" component of the
        // "view" object.
        // When the mouse wheel is moved, the "mouseWheelMoved" method will be called,
        // which will then
        // call the "logZoom" method with the MouseWheelEvent and
        // "modelOtherPerspective" as
        // parameters.
        this.view.getVue3().addMouseWheelListener(new MouseWheelListener() {
            /**
             * This function logs the zoom event and updates the model for a different
             * perspective.
             * 
             * @param e The parameter "e" is a MouseWheelEvent object that represents the
             *          mouse wheel
             *          movement event. It contains information about the event, such as the
             *          amount and direction
             *          of the wheel movement.
             */
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                logZoom(e, modelOtherPerspective);
            }
        });

        // The above code is adding a MouseListener to the image label in the view. The
        // MouseListener
        // has several methods that handle different mouse events:
        this.view.getVue2().getImageLabel().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            /**
             * The function stores the coordinates of the mouse click event in the lastPoint
             * variable.
             * 
             * @param e The parameter "e" is an instance of the MouseEvent class. It
             *          represents the
             *          event that occurred when the mouse button was pressed.
             */
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();

            }

            /**
             * The function saves the state of the modelPerspective and its flag when the
             * mouse is
             * released.
             * 
             * @param e The parameter "e" is an instance of the MouseEvent class, which
             *          represents a
             *          mouse event that occurred. It contains information about the event,
             *          such as the mouse
             *          position and the type of event (e.g., mouse released, mouse clicked,
             *          etc.).
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                lastPoint = null;
                caretaker.saveState(modelPerspective.saveToMemento(), modelPerspective.isFlag());

            }

            /**
             * The function sets focus on a specific component when the mouse enters it.
             * 
             * @param e The parameter "e" in the method "mouseEntered" is of type
             *          MouseEvent. It
             *          represents the event that occurred when the mouse entered the
             *          component.
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                view.getVue2().setFocusable(true);
                view.getVue2().requestFocusInWindow();

            }

            /**
             * The function sets the focusable property of a component to false when the
             * mouse exits
             * the component.
             * 
             * @param e The parameter "e" is an instance of the MouseEvent class, which
             *          represents a
             *          mouse event that occurred. It contains information about the event,
             *          such as the source
             *          of the event, the location of the mouse, and the type of event
             *          (e.g., mouse entered,
             *          mouse exited, mouse clicked,
             */
            @Override
            public void mouseExited(MouseEvent e) {
                view.getVue2().setFocusable(false);

            }
        });

        // The above code is adding a MouseMotionListener to the image label in the
        // view. This listener
        // is responsible for handling mouse drag events. When the mouse is dragged, the
        // code
        // calculates the difference between the current mouse position and the last
        // recorded mouse
        // position. It then creates a Translate command with the modelPerspective and
        // the calculated
        // delta, and executes the command. This allows the image to be translated
        // (moved) based on the
        // mouse drag movement.
        this.view.getVue2().getImageLabel().addMouseMotionListener(new MouseMotionListener() {

            /**
             * This function handles the dragging of the mouse and updates the position of
             * an object
             * accordingly.
             * 
             * @param e The parameter "e" is an instance of the MouseEvent class, which
             *          represents a
             *          mouse event. It contains information about the event, such as the
             *          position of the mouse
             *          cursor and the type of event (e.g., mouse pressed, mouse released,
             *          mouse dragged, etc.).
             *          In this case, the method
             */
            @Override
            public void mouseDragged(MouseEvent e) {

                if (lastPoint != null) {
                    Point currentPoint = e.getPoint();
                    Point delta = new Point(currentPoint.x - lastPoint.x, currentPoint.y - lastPoint.y);
                    Translate commandTraslante = new Translate(modelPerspective, delta);
                    commandTraslante.execute();
                    lastPoint = currentPoint;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

        });

        // The above code is adding a MouseListener to the image label in the view. The
        // MouseListener
        // has several methods that are implemented:
        this.view.getVue3().getImageLabel().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            /**
             * The function sets the value of otherLastPoint to the current mouse position
             * and prints
             * "mousePressed" to the console.
             * 
             * @param e The parameter "e" is an instance of the MouseEvent class, which
             *          represents a
             *          mouse event. It contains information about the event, such as the
             *          location of the mouse
             *          cursor and the type of mouse event that occurred. In this case, the
             *          method is called
             *          when a mouse button is pressed.
             */
            @Override
            public void mousePressed(MouseEvent e) {
                otherLastPoint = e.getPoint();

            }

            /**
             * This function saves the state of the model and its flag when the mouse is
             * released.
             * 
             * @param e The parameter "e" is of type MouseEvent and represents the event
             *          that occurred
             *          when the mouse button was released. It contains information about
             *          the mouse position,
             *          the source of the event, and other relevant details.
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                otherLastPoint = null;

                caretaker.saveState(modelOtherPerspective.saveToMemento(), modelOtherPerspective.isFlag());

            }

            /**
             * The function sets focus on a specific component when the mouse enters it.
             * 
             * @param e The parameter "e" is an instance of the MouseEvent class, which
             *          represents a
             *          mouse event that occurred. It contains information about the event,
             *          such as the source
             *          of the event, the location of the mouse, and the type of event
             *          (e.g., mouse entered,
             *          mouse clicked, etc.).
             */
            @Override
            public void mouseEntered(MouseEvent e) {

                view.getVue3().setFocusable(true);
                view.getVue3().requestFocusInWindow();

            }

            /**
             * The function sets the focusable property of a component to false when the
             * mouse exits the
             * component.
             * 
             * @param e The parameter "e" is an instance of the MouseEvent class, which
             *          represents a
             *          mouse event that occurred. It contains information about the event,
             *          such as the source of
             *          the event, the location of the mouse, and the type of event (e.g.,
             *          mouse entered, mouse
             *          exited, mouse clicked,
             */
            @Override
            public void mouseExited(MouseEvent e) {
                view.getVue3().setFocusable(false);

            }
        });

        // The above code is adding a MouseMotionListener to the image label in the Vue3
        // object of the
        // view. This listener is used to handle mouse dragging events. When the mouse
        // is dragged, the
        // code calculates the difference between the current mouse position and the
        // last mouse
        // position, and then creates a Translate command with the modelOtherPerspective
        // and the
        // calculated difference. This command is then executed. The last mouse position
        // is updated
        // with the current mouse position, and the position of the
        // modelOtherPerspective is printed to
        // the console.
        this.view.getVue3().getImageLabel().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (otherLastPoint != null) {
                    Point currentOtherPoint = e.getPoint();
                    Point alpha = new Point(currentOtherPoint.x - otherLastPoint.x,
                            currentOtherPoint.y - otherLastPoint.y);
                    Translate commandTraslante = new Translate(modelOtherPerspective, alpha);
                    commandTraslante.execute();
                    otherLastPoint = currentOtherPoint;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });

        // The above code is adding a KeyListener to the "vue2" component of the "view"
        // object. This
        // KeyListener listens for key events and performs certain actions when specific
        // keys are
        // pressed.
        this.view.getVue2().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            /**
             * This function handles key events in a Java program, including undoing and
             * redoing
             * actions, rotating an image, applying a filter, and adding a glitch effect.
             * 
             * @param e The parameter "e" is an instance of the KeyEvent class, which
             *          represents a key
             *          event. It contains information about the key that was pressed, such
             *          as the key code and
             *          whether certain modifier keys (such as Ctrl) were pressed at the
             *          same time.
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown()) {
                    Undo undo = new Undo(modelPerspective);
                    undo.execute();
                }
                if (e.getKeyCode() == KeyEvent.VK_Y && e.isControlDown()) {
                    Redo redo = new Redo(modelPerspective);
                    redo.execute();
                }
                if (e.getKeyCode() == KeyEvent.VK_R && e.isControlDown()) {
                    Rotate rotate = new Rotate(modelPerspective, 90);
                    rotate.execute();
                    centerImageInLabel(view.getVue3(), modelPerspective);
                    caretaker.saveState(modelPerspective.saveToMemento(), modelPerspective.isFlag());
                }
                if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) {
                    Filtre filtre = new Filtre(modelPerspective);
                    filtre.execute();
                }
                if (e.getKeyCode() == KeyEvent.VK_G && e.isControlDown()) {
                    Glitch glitch = new Glitch(modelPerspective);
                    glitch.execute();
                    caretaker.saveState(modelPerspective.saveToMemento(), modelPerspective.isFlag());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        // The above code is adding a key listener to the "Vue3" component of the "view"
        // object. This
        // key listener listens for key events and performs certain actions when
        // specific keys are
        // pressed.
        this.view.getVue3().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            /**
             * This function handles key events in a Java program, including undoing and
             * redoing
             * actions, rotating an image, applying a filter, and adding a glitch effect.
             * 
             * @param e The parameter "e" is an instance of the KeyEvent class, which
             *          represents a key
             *          event. It contains information about the key that was pressed, such
             *          as the key code and
             *          whether certain modifier keys (such as Ctrl) were pressed at the
             *          same time.
             */
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown()) {
                    Undo undo = new Undo(modelOtherPerspective);
                    undo.execute();
                }
                if (e.getKeyCode() == KeyEvent.VK_Y && e.isControlDown()) {
                    Redo redo = new Redo(modelOtherPerspective);
                    redo.execute();
                }
                if (e.getKeyCode() == KeyEvent.VK_R && e.isControlDown()) {
                    Rotate rotate = new Rotate(modelOtherPerspective, 90);
                    rotate.execute();
                    centerImageInLabel(view.getVue3(), modelOtherPerspective);
                    caretaker.saveState(modelOtherPerspective.saveToMemento(), modelOtherPerspective.isFlag());
                }
                if (e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()) {
                    Filtre filtre = new Filtre(modelOtherPerspective);
                    filtre.execute();
                }
                if (e.getKeyCode() == KeyEvent.VK_G && e.isControlDown()) {
                    Glitch glitch = new Glitch(modelOtherPerspective);
                    glitch.execute();
                    caretaker.saveState(modelOtherPerspective.saveToMemento(), modelOtherPerspective.isFlag());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /**
     * The getView() function returns a UserView object.
     * 
     * @return The method is returning an object of type UserView.
     */
    public UserView getView() {
        return view;
    }

    /**
     * The getModelImage() function returns an Image object.
     * 
     * @return The method is returning an Image object.
     */
    public Image getModelImage() {
        return modelImage;
    }

    /**
     * The function returns the model perspective.
     * 
     * @return The method is returning an object of type Perspective.
     */
    public Perspective getModelPerspective() {
        return modelPerspective;
    }

    /**
     * The function returns the modelOtherPerspective object.
     * 
     * @return The method is returning an object of type OtherPerspective.
     */
    public OtherPerspective getModelOtherPerspective() {
        return modelOtherPerspective;
    }

    /**
     * The loadImage() function allows the user to select an image file and sets it
     * as the image for
     * three different perspectives in a model.
     */
    /**
     * The loadImage() function allows the user to select an image file and display
     * it in multiple
     * perspectives within a graphical user interface.
     */
    public void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp", "jpeg",
                "ser");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            String fileExtension = getFileExtension(selectedFile);

            if ("ser".equals(fileExtension)) {
                // Traitement pour les fichiers .ser
                readFile(filePath);
                caretaker.getMementoListOtherPerspective().clear();
                caretaker.getMementoListPerspective().clear();

                // ... Traitez et utilisez les objets désérialisés ici ...
            } else {

                ImageIcon icon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());

                this.modelImage.setImageIcon(icon);
                this.modelPerspective.setImageIcon(icon);
                this.modelOtherPerspective.setImageIcon(icon);

                this.modelPerspective.setPosition(centerImageInLabel(this.view.getVue2(), this.modelPerspective));
                this.modelOtherPerspective
                        .setPosition(centerImageInLabel(this.view.getVue3(), this.modelOtherPerspective));

                caretaker.getMementoListOtherPerspective().clear();
                caretaker.getMementoListPerspective().clear();

                this.modelPerspective.setZoomFactor(1.0);
                this.modelOtherPerspective.setZoomFactor(1.0);

                caretaker.saveState(this.modelPerspective.saveToMemento(), modelPerspective.isFlag());
                caretaker.saveState(this.modelOtherPerspective.saveToMemento(), modelOtherPerspective.isFlag());
            }
        }
    }

    /**
     * The function saves a list of objects to a serialized file.
     */
    /**
     * This function saves a list of objects to a file using serialization.
     */
    public void saveFile() {
        List<Object> objets = new ArrayList<>();

        objets.add(modelImage);
        objets.add(modelPerspective);
        objets.add(modelOtherPerspective);
        objets.add(view.getVue1());
        objets.add(view.getVue2());
        objets.add(view.getVue3());

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers de serialisation", "ser"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            this.filePath = fileToSave.getAbsolutePath();
            if (!this.filePath.endsWith(".ser")) {
                this.filePath += ".ser";
            }
            prefs.put(FILE_PATH_KEY, this.filePath);
            serialization.serializeObjects(objets, this.filePath);
        }

    }

    /**
     * The function reads objects from a serialized file and assigns their values to
     * corresponding
     * model objects.
     */
    /**
     * The function reads a file, deserializes the objects in the file, and updates
     * the corresponding
     * models and caretaker.
     */
    public void readFile() {

        if (this.filePath == null || this.filePath.isEmpty()) {
            
            return;
        }

        List<Object> objetsLus = serialization.deserializeObjects(this.filePath);

        for (Object objet : objetsLus) {
            if (objet instanceof Perspective) {
                Perspective perspective = (Perspective) objet;
                modelPerspective.setImageIcon(perspective.getImageIcon());
                modelPerspective.setPosition(perspective.getPosition());
                modelPerspective.setZoomFactor(perspective.getZoomFactor());
                caretaker.saveState(perspective.saveToMemento(), modelPerspective.isFlag());
            } else if (objet instanceof OtherPerspective) {
                OtherPerspective perspective = (OtherPerspective) objet;
                modelOtherPerspective.setImageIcon(perspective.getImageIcon());
                modelOtherPerspective.setPosition(perspective.getPosition());
                modelOtherPerspective.setZoomFactor(perspective.getZoomFactor());
                caretaker.saveState(perspective.saveToMemento(), perspective.isFlag());
            } else if (objet instanceof Image) {
                Image image = (Image) objet;
                modelImage.setImageIcon(image.getImageIcon());
            }
        }

    }

    /**
     * The function reads a file and deserializes the objects stored in it, updating
     * the corresponding
     * models and saving their states.
     * 
     * @param pathing The parameter "pathing" is a String that represents the path
     *                to a file that needs
     *                to be read.
     */
    public void readFile(String pathing) {

        if (pathing == null || pathing.isEmpty()) {
            return;
        }

        List<Object> objetsLus = serialization.deserializeObjects(pathing);

        for (Object objet : objetsLus) {
            if (objet instanceof Perspective) {
                Perspective perspective = (Perspective) objet;
                modelPerspective.setImageIcon(perspective.getImageIcon());
                modelPerspective.setPosition(perspective.getPosition());
                modelPerspective.setZoomFactor(perspective.getZoomFactor());
                caretaker.saveState(perspective.saveToMemento(), modelPerspective.isFlag());
            } else if (objet instanceof OtherPerspective) {
                OtherPerspective perspective = (OtherPerspective) objet;
                modelOtherPerspective.setImageIcon(perspective.getImageIcon());
                modelOtherPerspective.setPosition(perspective.getPosition());
                modelOtherPerspective.setZoomFactor(perspective.getZoomFactor());
                caretaker.saveState(perspective.saveToMemento(), perspective.isFlag());
            } else if (objet instanceof Image) {
                Image image = (Image) objet;
                modelImage.setImageIcon(image.getImageIcon());
            }
        }

    }

    /**
     * The function "displayView" calls the "display" method of the "view" object.
     */
    public void displayView() {
        view.display();
    }

    /**
     * The function `centerImageInLabel` calculates the coordinates of the center
     * point in order to
     * position an image in the center of a label.
     * 
     * @param panel       The parent panel that contains the image label.
     * @param perspective The `perspective` parameter is an object of type
     *                    `IPerspective`. It likely
     *                    represents some kind of perspective or view of an image.
     * @return The method is returning a Point object, which represents the
     *         coordinates of the center
     *         point for positioning the image in the label.
     */
    // The above code is defining a method called "centerImageInLabel" that takes
    // two parameters: a
    // ParentPanel object called "panel" and an IPerspective object called
    // "perspective". The purpose
    // of this method is not clear from the provided code snippet. It is likely that
    // the method is
    // intended to center an image within a label or perform some other operation
    // related to image
    // positioning within a panel. However, without further context or code, it is
    // not possible to
    // determine the exact functionality of this method.
    public Point centerImageInLabel(ParentPanel panel, IPerspective perspective) {

        if (perspective.getImageIcon() != null) {
            // Dimensions du label
            int labelWidth = panel.getImageLabel().getWidth();
            int labelHeight = panel.getImageLabel().getHeight();

            // Dimensions de l'image
            int imageWidth = perspective.getImageIcon().getIconWidth();
            int imageHeight = perspective.getImageIcon().getIconHeight();

            // Calculer le point central pour positionner l'image
            x = (labelWidth - imageWidth) / 2;
            y = (labelHeight - imageHeight) / 2;
            // Mettre à jour la position initiale de l'image dans le modèle

        }

        return new Point(x, y);
    }

    /**
     * The logZoom function logs whether a zoom in or zoom out command is being
     * executed and then
     * executes the corresponding command.
     * 
     * @param e                 The parameter "e" is a MouseWheelEvent object that
     *                          represents the mouse wheel event
     *                          that occurred.
     * @param choixIPerspective The parameter "choixIPerspective" is of type
     *                          IPerspective, which is
     *                          likely an interface or a class representing a
     *                          perspective in a graphical user interface. It is
     *                          used as an argument in the constructor of the
     *                          ZoomInCommand and ZoomOutCommand classes,
     *                          indicating the perspective on which the zoom
     *                          operation should
     */
    public void logZoom(MouseWheelEvent e, IPerspective choixIPerspective) {

        if (e.getWheelRotation() < 0) {
            ZoomInCommand command = new ZoomInCommand(choixIPerspective, 1.1);
            command.execute();

        } else {
            ZoomOutCommand command = new ZoomOutCommand(choixIPerspective, 1.1);
            command.execute();
        }
    }

    /**
     * The getFileExtension function returns the file extension of a given file.
     * 
     * @param file The "file" parameter is of type File and represents the file for
     *             which we want to
     *             get the file extension.
     * @return The method is returning the file extension of the given file.
     */
    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        } else {
            return "";
        }
    }
}
