/**
 * The MenuBarBuilder class is a Java class that creates a menu bar with a file menu and two menu items
 * for charging and saving.
 */
package view;

import javax.swing.*;

public class MenuBarBuilder extends JMenuBar {

    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuItemCharge;
    private JMenuItem menuItemSave;
    private JMenu menuHelp;
    private JMenuItem menuItemShortcut;

    // The `public MenuBarBuilder()` is a constructor method for the
    // `MenuBarBuilder` class. It is
    // called when a new instance of the `MenuBarBuilder` class is created.
    public MenuBarBuilder() {
        initComp();
    }

    /**
     * The function initializes a menu bar and adds menu items for "Charge" and
     * "Sauvegarder" to the
     * "Fichier" menu.
     */
    private void initComp() {

        menuBar = new JMenuBar();
        menuFile = new JMenu("Fichier");
        menuHelp = new JMenu("Help");
        menuItemCharge = new JMenuItem("Charge");
        menuItemSave = new JMenuItem("Sauvegarder");
        menuItemShortcut = new JMenuItem("Shortcut");
        
        
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
        menuHelp.add(menuItemShortcut);
        menuFile.add(menuItemCharge);
        menuFile.add(menuItemSave);

    }

    /**
     * The function returns a JMenuBar object.
     * 
     * @return The method is returning a JMenuBar object.
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * The function returns a JMenu object named "menuFile".
     * 
     * @return The method is returning a JMenu object.
     */
    public JMenu getMenuFile() {
        return menuFile;
    }

    /**
     * The function returns a JMenuItem object named menuItemCharge.
     * 
     * @return The method is returning a JMenuItem object.
     */
    public JMenuItem getMenuItemCharge() {
        return menuItemCharge;
    }

    /**
     * The function returns the JMenuItem object for the "Save" menu item.
     * 
     * @return The method is returning a JMenuItem object.
     */
    public JMenuItem getMenuItemSave() {
        return menuItemSave;
    }

    /**
     * The function returns a JMenu object named menuHelp.
     * 
     * @return The method is returning a JMenu object.
     */
    public JMenu getMenuHelp() {
        return menuHelp;
    }

    /**
     * The function returns a JMenuItem object representing a menu item shortcut.
     * 
     * @return The method is returning a JMenuItem object.
     */
    public JMenuItem getMenuItemShortcut() {
        return menuItemShortcut;
    }
}
