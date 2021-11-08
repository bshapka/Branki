package ui.gui.views.windows.photo;

import javax.swing.*;

// represents a popup window that displays a photo
public class PhotoPopupWindow {

    private final JDialog dialog;
    private final JLabel label;

    // EFFECTS: constructs PhotoPopup using given filePath
    public PhotoPopupWindow(String filePath) {
        dialog = new JDialog();
        label = new JLabel(new ImageIcon(filePath));
        dialog.add(label);
        dialog.pack();
        dialog.setVisible(true);
    }

}
