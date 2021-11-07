package ui.gui.views;

import ui.gui.GUI;

import javax.swing.*;
import java.util.Arrays;

// represents the menu bar associated with the main menu
class MainMenuBar extends JMenuBar {

    // EFFECTS: adds menus along with menu items
    MainMenuBar() {
        addMenus();
        addDecksMenuItems();
        addCardsMenuItems();
        addDataMenuItems();
        addStudyMenuItems();
        addBreakMenuItems();
    }

    // MODIFIES: this
    // EFFECTS: adds menus to this
    private void addMenus() {
        String[] menuTitles = new String[]{
                "Decks", "Cards", "Data", "Study", "Take Break"};
        Arrays.stream(menuTitles).forEach(title -> this.add(new JMenu(title)));
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to decks menu in this
    private void addDecksMenuItems() {
        String[] menuItemNames = new String[]{
                "View", "Create", "Modify", "Delete"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(0).add(menuItem);
        });
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to cards menu in this
    private void addCardsMenuItems() {
        String[] menuItemNames = new String[]{
                "View", "Create", "Modify", "Delete"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(1).add(menuItem);
        });
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to data menu in this and adds action listener
    private void addDataMenuItems() {
        String[] menuItemNames = new String[]{"Save", "Load"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(2).add(menuItem);
            addDataMenuItemActionListener(menuItem, name);
        });
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to study menu in this and adds action listener
    private void addStudyMenuItems() {
        String[] menuItemNames = new String[]{"Start Session"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(3).add(menuItem);
            addStudyMenuItemActionListener(menuItem, name);
        });
    }

    // REQUIRES: menuItem is not null, name is "Start Session"
    // MODIFIES: menuItem
    // EFFECTS: adds action listener to menuItem
    private void addStudyMenuItemActionListener(JMenuItem menuItem, String name) {
        if (name.equals("Start Session")) {
            menuItem.addActionListener(e -> GUI.showDeckSelector());
        }
    }

    // REQUIRES: menuItem is not null, name is "Save" or "Load"
    // MODIFIES: menuItem
    // EFFECTS: adds action listener to menuItem
    private void addDataMenuItemActionListener(JMenuItem menuItem, String name) {
        if (name.equals("Save")) {
            menuItem.addActionListener(e -> GUI.saveDecksAndNotify());
        }
        if (name.equals("Load")) {
            menuItem.addActionListener(e -> GUI.loadDecksAndNotify());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds menu items to break menu in this and adds action listener
    private void addBreakMenuItems() {
        String[] menuItemNames = new String[]{"Cat Loaf", "Toby"};
        Arrays.stream(menuItemNames).forEach(name -> {
            JMenuItem menuItem = new JMenuItem(name);
            this.getMenu(4).add(menuItem);
            addBreakMenuItemActionListener(menuItem, name);
        });
    }

    // REQUIRES: menuItem is not null, name is "Cat Loaf" or "Toby"
    // MODIFIES: menuItem
    // EFFECTS: adds action listener to menuItem
    private void addBreakMenuItemActionListener(JMenuItem menuItem, String name) {
        if (name.equals("Cat Loaf")) {
            menuItem.addActionListener(e -> GUI.showCatLoaf());
        }
        if (name.equals("Toby")) {
            menuItem.addActionListener(e -> GUI.showToby());
        }
    }

}