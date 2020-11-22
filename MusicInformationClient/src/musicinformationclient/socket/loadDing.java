package musicinformationclient.socket;

import javax.swing.*;

public class loadDing {
    public JPanel loadingPanel() {
        JPanel panel = new JPanel();
        BoxLayout layoutMgr = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layoutMgr);

        ClassLoader cldr = this.getClass().getClassLoader();
        java.net.URL imageURL   = cldr.getResource("../img/200.gif");
        ImageIcon imageIcon = new ImageIcon(imageURL);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(imageIcon);
        imageIcon.setImageObserver(iconLabel);

        JLabel label = new JLabel("Loading...");
        panel.add(iconLabel);
        panel.add(label);
        return panel;
    }
}
