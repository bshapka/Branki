package ui.gui.views;

import model.Deck;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// represents a deck selector for selecting a deck from a given lists of decks
public abstract class DeckSelector extends JFrame {

    private JPanel panel;
    protected JList list;
    protected JButton submitButton;
    protected final List<Deck> decks;

    // EFFECTS: configures list, button, panel, and frame
    public DeckSelector(List<Deck> decks) {
        this.decks = decks;
        setName("Study Deck Selection");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupList(decks);
        setupSubmitButton();
        setupPanel();
        add(panel);
        pack();
        setSize(MainWindow.WIDTH / 2, MainWindow.HEIGHT / 2);
    }

    // MODIFIES: this
    // EFFECTS: instantiates panel and sets its layout
    private void setupPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.NORTH, new JScrollPane(list));
        panel.add(BorderLayout.SOUTH, submitButton);
    }

    // MODIFIES: this
    // EFFECTS: instantiates button with set text, and adds action listener
    protected abstract void setupSubmitButton();

    // MODIFIES: this
    // EFFECTS: instantiates list with deck names in decks and sets configuration options
    private void setupList(List<Deck> decks) {
        list = new JList(decks.stream().map(Deck::getName).toArray());
        list.setVisibleRowCount(5);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
    }

}
