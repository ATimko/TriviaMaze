package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameWindow extends JFrame implements ActionListener {
    JMenuItem startMenuItem;

    /**
     * Menu item to reset the game.
     */
    JMenuItem resetMenuItem;
    JLabel gameTitle = new JLabel();
    public gameWindow() throws HeadlessException {
        setTitle("Trivia Maze");
        setSize(720, 480);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");

        startMenuItem = new JMenuItem("Start");
        resetMenuItem = new JMenuItem("Reset");
        /**
         * Menu item to exit the game.
         */
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        gameMenu.add(startMenuItem);
        gameMenu.add(resetMenuItem);
        gameMenu.add(exitMenuItem);


        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutMenuItem = new JMenuItem("About");
        JMenuItem rulesMenuItem = new JMenuItem("Rules");
        JMenuItem shortcutsMenuItem = new JMenuItem("Shortcuts");

        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);
        helpMenu.add(shortcutsMenuItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        //JFrame.add(textfield);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}