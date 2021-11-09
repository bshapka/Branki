package ui.gui.views.selectors;

import model.Selectable;
import ui.gui.views.windows.main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// represents a selector for selecting an object from a list
public abstract class Selector extends JFrame {

    private JPanel panel;
    protected JList list;
    protected JButton submitButton;
    protected final List<Selectable> selectables;

    // EFFECTS: configures list, button, panel, and frame
    public Selector(List<Selectable> selectables, String name) {
        this.selectables = selectables;
        setupFrame(name);
        setupList(selectables);
        setupSubmitButton();
        setupPanel();
        add(panel);
        pack();
        setSize(MainWindow.WIDTH / 2, MainWindow.HEIGHT / 2);
    }

    // MODIFIES: this
    // EFFECTS: sets name, title, and close operation of frame
    private void setupFrame(String name) {
        setName(name);
        setTitle(name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: instantiates panel and sets its layout
    private void setupPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.CENTER, new JScrollPane(list));
        panel.add(BorderLayout.SOUTH, submitButton);
    }

    // MODIFIES: this
    // EFFECTS: instantiates button with set text, and adds action listener
    protected abstract void setupSubmitButton();

    // MODIFIES: this
    // EFFECTS: instantiates list with descriptions in selectables and sets configuration options
    private void setupList(List<Selectable> selectables) {
        list = new JList(selectables.stream().map(Selectable::getDescription).toArray());
        list.setVisibleRowCount(5);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
    }

}
