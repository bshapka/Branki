package ui.gui.views;

import exceptions.NoDecksWithCardsException;
import model.Deck;
import ui.gui.GUI;
import ui.gui.enums.DialogMessage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// represents a deck selector for selecting a deck from a given lists of decks
public class DeckSelector extends JFrame {

    private JPanel panel;
    private JList list;
    private JButton submitButton;
    private List<Deck> decksWithCards;

    // EFFECTS: throws NoDecksWithCardsException if decksWithCards contains a deck with no cards,
    //          otherwise configures list, button, panel, and frame
    public DeckSelector(List<Deck> decksWithCards) throws NoDecksWithCardsException {
        if (!decksWithCards.stream().allMatch(Deck::hasCards)) {
            throw new NoDecksWithCardsException(DialogMessage.NO_DECKS_WITH_CARDS.getMessage());
        }
        this.decksWithCards = decksWithCards;
        setName("Study Deck Selection");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupList(decksWithCards);
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
    private void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showStudyDeckWindow(decksWithCards.get(list.getSelectedIndex())));
    }

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
