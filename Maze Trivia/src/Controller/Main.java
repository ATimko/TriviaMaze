package Controller;

import views.gameWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(gameWindow::new);
    }
}
