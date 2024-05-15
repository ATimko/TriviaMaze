package views;

import javax.swing.*;
import java.awt.*;

public class aboutUI extends JFrame {

    private final JPanel myContainer;

    public aboutUI() {
        super("About");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        myContainer = new JPanel();
        this.add(myContainer);

        createUI();
    }

    private void createUI() {
        myContainer.setPreferredSize(this.getPreferredSize());
        myContainer.setLayout(new BoxLayout(myContainer, BoxLayout.Y_AXIS));

        final JLabel creditsTextLabel = new JLabel();
        creditsTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditsTextLabel.setText("Credits: Developed by [Your Name]");
        myContainer.add(creditsTextLabel);
    }
}
