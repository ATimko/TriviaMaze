package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameWindow extends JFrame implements ActionListener {
    public gameWindow() throws HeadlessException {
        setTitle("Game Of Craps");
        setSize(720, 480);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}