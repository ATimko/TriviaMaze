package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameWindow extends JFrame implements ActionListener {
    JMenuItem saveGameStatus;

    /**
     * Menu item to reset the game.
     */
    JMenuItem loadSavedGame;
    JLabel gameTitle = new JLabel();
    public gameWindow() throws HeadlessException {
        setTitle("Trivia Maze");
        setSize(720, 480);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("File");

        saveGameStatus = new JMenuItem("Save Game");
        loadSavedGame = new JMenuItem("Load Game");
        /**
         * Menu item to exit the game.
         */
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        gameMenu.add(saveGameStatus);
        gameMenu.add(loadSavedGame);
        gameMenu.add(exitMenuItem);


        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutMenuItem = new JMenuItem("About");
        JMenuItem rulesMenuItem = new JMenuItem("Rules");


        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);


        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        //JFrame.add(textfield);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}