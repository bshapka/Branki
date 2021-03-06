package ui.gui.views.windows.main;

import ui.gui.GUI;

import javax.swing.*;
import java.util.Arrays;

// represents the menu bar associated with the main menu
public class MainMenuBar extends JMenuBar {

    // EFFECTS: adds menus along with menu items
    public MainMenuBar() {
        addMenus();
        addDecksMenuItems();
        addCardsMenuItems();
        addDataMenuItems();
        addStudyMenuItems();
        addBreakMenuItems();
    }

    // MODIFIES: this
    // EFFECTS: adds menus
    private void addMenus() {
        String[] menuTitles = new String[]{
                "Decks", "Cards", "Data", "Study", "Take Break"};
        Arrays.stream(menuTitles).forEach(title -> this.add(new JMenu(title)));
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to decks menu
    private void addDecksMenuItems() {
        String[] menuItemNames = new String[]{"Create", "Edit"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(0).add(menuItem);
            addDeckMenuItemActionListener(menuItem, name);
        });
    }

    // MODIFIES: menuItem
    // EFFECTS: adds action listener to menuItem
    private void addDeckMenuItemActionListener(JMenuItem menuItem, String name) {
        if (name.equals("Create")) {
            menuItem.addActionListener(e -> GUI.showCreateDeckWindow());
        } else if (name.equals("Edit")) {
            menuItem.addActionListener(e -> GUI.showEditDeckSelector());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to cards menu
    private void addCardsMenuItems() {
        String[] menuItemNames = new String[]{"Create", "Edit"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(1).add(menuItem);
            addCardMenuItemActionListener(menuItem, name);
        });
    }

    // MODIFIES: menuItem
    // EFFECTS: adds action listener to menuItem
    private void addCardMenuItemActionListener(JMenuItem menuItem, String name) {
        if (name.equals("Create")) {
            menuItem.addActionListener(e -> GUI.showCreateCardDeckSelector());
        } else if (name.equals("Edit")) {
            menuItem.addActionListener(e -> GUI.showEditCardDeckSelector());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to data menu and adds action listener
    private void addDataMenuItems() {
        String[] menuItemNames = new String[]{"Save", "Load"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(2).add(menuItem);
            addDataMenuItemActionListener(menuItem, name);
        });
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to study menu and adds action listener
    private void addStudyMenuItems() {
        String[] menuItemNames = new String[]{"Start Session"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(3).add(menuItem);
            addStudyMenuItemActionListener(menuItem, name);
        });
    }

    // MODIFIES: menuItem
    // EFFECTS: adds action listener to menuItem
    private void addStudyMenuItemActionListener(JMenuItem menuItem, String name) {
        if (name.equals("Start Session")) {
            menuItem.addActionListener(e -> GUI.showStudyDeckSelector());
        }
    }

    // MODIFIES: menuItem
    // EFFECTS: adds action listener to menuItem
    private void addDataMenuItemActionListener(JMenuItem menuItem, String name) {
        if (name.equals("Save")) {
            menuItem.addActionListener(e -> GUI.saveDecksAndNotify());
        } else if (name.equals("Load")) {
            menuItem.addActionListener(e -> GUI.loadDecksAndNotify());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to break menu and adds action listener
    private void addBreakMenuItems() {
        String[] menuItemNames = new String[]{"Cat Loaf", "Toby"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(4).add(menuItem);
            addBreakMenuItemActionListener(menuItem, name);
        });
    }

    // MODIFIES: menuItem
    // EFFECTS: adds action listener to menuItem
    private void addBreakMenuItemActionListener(JMenuItem menuItem, String name) {
        if (name.equals("Cat Loaf")) {
            menuItem.addActionListener(e -> GUI.showCatLoaf());
        } else if (name.equals("Toby")) {
            menuItem.addActionListener(e -> GUI.showToby());
        }
    }

}