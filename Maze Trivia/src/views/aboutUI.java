/*
 *  TCSS 305 - Winter 2023
 *  Group 2 - Tetris
 */

package views;

import Common.mazeConstants;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class aboutUI extends JFrame {

    /**
     * Container to hold body in.
     */
    private final JPanel myContainer;

    /**
     * Entry point to create a new window for About.
     */
    public aboutUI() {
        super("About");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(mazeConstants.Dimensions.ABOUT_PANEL_DIMENSION);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        myContainer = new JPanel();
        this.add(myContainer);

        createUI();
    }

    /**
     * Creates the UI.
     */
    private void createUI() {
        myContainer.setPreferredSize(this.getPreferredSize());
        myContainer.setLayout(new BoxLayout(myContainer, BoxLayout.Y_AXIS));

        final JLabel creditsTextLabel = new JLabel();
        creditsTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditsTextLabel.setText("");
        myContainer.add(creditsTextLabel);

    }


}
