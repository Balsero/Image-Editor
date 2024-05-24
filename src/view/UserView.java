/**
 * The UserView class is a Java class that represents the graphical user interface for an image
 * application, including the main frame, panels, and menu bar.
 */
package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import modele.OtherPerspective;
import modele.Perspective;
import modele.Image;

public class UserView {

    private JFrame frame;
    private JPanel mainPanel;
    private ParentPanel vue1, vue2, vue3;
    private MenuBarBuilder menuBarBuilder;

    /*
     * ATTENTION : il faut jamais , jamais , jamais faire de getters et setters pour
     * les trois modele
     * leur unique but pourquoi ils sont la dans le constructeur est pour instancie
     * les trois panneau et
     * c'est tout.
     * Si on veut faire des modification dans les model il faut passer par le
     * controlleur , et si on veut voir
     * les resultat dans la view des changements dans le model , pas le choix il
     * faut l'implementer dans le update() de
     * PerspectivePanel et OtherPerspectivePanel.
     */

    private Image modelImage;
    private Perspective modelPerspective;
    private OtherPerspective modelOtherPerspective;

    public UserView(Image modelImage, Perspective modelPerspective, OtherPerspective modelOtherPerspective) {

        this.modelImage = modelImage;
        this.modelPerspective = modelPerspective;
        this.modelOtherPerspective = modelOtherPerspective;



        frame = new JFrame("Image Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        

        // Initialisation du panneau Principal
        mainPanel = new JPanel(new GridLayout(1, 3));// Layout pour trois panneaux de taille egale

        // Initialisation des troix panneau des vues
        vue1 = new ImagePanel(this.modelImage);
        vue2 = new PerspectivePanel(this.modelPerspective);
        vue3 = new OtherPerspectivePanel(this.modelOtherPerspective);

        // set des Borders
        vue2.setBorder((new MatteBorder(0, 1, 0, 1, Color.BLACK)));

        // Ajout des trois panneau des vues dans la panneauPrincipal
        mainPanel.add(vue1);
        mainPanel.add(vue2);
        mainPanel.add(vue3);

        // Creation de la bar de Menu
        menuBarBuilder = new MenuBarBuilder();

        frame.add(mainPanel);
        frame.setJMenuBar(menuBarBuilder.getMenuBar());
        frame.pack(); // Ajuste la taille du frame selon les composants

        frame.setLocationRelativeTo(null); // Centre la fenêtre
    }

    /**
     * The function returns a JFrame object.
     * 
     * @return The method is returning a JFrame object.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * The function returns the main panel of a Java Swing application.
     * 
     * @return The method is returning a JPanel object.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * The function returns the ParentPanel object named vue1.
     * 
     * @return The method is returning an object of type ParentPanel, specifically
     *         the object named
     *         vue1.
     */
    public ParentPanel getVue1() {
        return vue1;
    }

    /**
     * The function returns the ParentPanel object named vue2.
     * 
     * @return The method is returning an object of type ParentPanel, specifically
     *         the object named
     *         vue2.
     */
    public ParentPanel getVue2() {
        return vue2;
    }

    /**
     * The function returns the ParentPanel object named vue3.
     * 
     * @return The method is returning an object of type ParentPanel, specifically
     *         the object named
     *         vue3.
     */
    public ParentPanel getVue3() {
        return vue3;
    }

    /**
     * The function sets the value of the variable "vue1" to the provided
     * "ParentPanel" object.
     * 
     * @param vue1 The parameter "vue1" is of type ParentPanel.
     */
    public void setVue1(ParentPanel vue1) {
        this.vue1 = vue1;
    }

    /**
     * The function sets the value of the "vue2" variable to the provided
     * "ParentPanel" object.
     * 
     * @param vue2 The parameter "vue2" is of type ParentPanel, which is a class
     *             representing a panel
     *             in a graphical user interface.
     */
    public void setVue2(ParentPanel vue2) {
        this.vue2 = vue2;
    }

    /**
     * The function sets the value of the "vue3" variable to the provided
     * "ParentPanel" object.
     * 
     * @param vue3 The parameter "vue3" is of type ParentPanel.
     */
    public void setVue3(ParentPanel vue3) {
        this.vue3 = vue3;
    }

    /**
     * The function returns a MenuBarBuilder object.
     * 
     * @return The method is returning a MenuBarBuilder object.
     */
    public MenuBarBuilder getMenuBarBuilder() {
        return menuBarBuilder;
    }

    /**
     * The function sets the size, visibility, and location of a frame in a Java
     * application.
     */
    public void display() {
        frame.setSize(1200, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
