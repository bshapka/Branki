package ui.gui.views;

import javax.swing.*;

// represents a popup pane that displays a photo
public class PhotoPopup {

    private final JDialog dialog;
    private final JLabel label;

    // EFFECTS: constructs PhotoPopup using given filePath
    PhotoPopup(String filePath) {
        dialog = new JDialog();
        label = new JLabel(new ImageIcon(filePath));
        dialog.add(label);
        dialog.pack();
        dialog.setVisible(true);
    }

}
