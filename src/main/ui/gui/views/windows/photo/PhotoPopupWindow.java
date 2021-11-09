package ui.gui.views.windows.photo;

import javax.swing.*;

// represents a popup window that displays a photo
public class PhotoPopupWindow {

    // EFFECTS: constructs PhotoPopup using given filePath
    public PhotoPopupWindow(String filePath) {
        JDialog dialog = new JDialog();
        JLabel label = new JLabel(new ImageIcon(filePath));
        dialog.add(label);
        dialog.pack();
        dialog.setVisible(true);
    }

}
