package views;

import maze.Room;
import maze.Door;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class RoomUI extends JPanel {

    private Image backgroundImage;
    private Room room;
    private JButton[] doorButtons;

    public RoomUI() {
        setLayout(new BorderLayout());
        // This is how big the panel is going to be
        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        gridPanel.setPreferredSize(new Dimension(200, 200));
        //gridPanel.setBounds(100,300,300,300);

        // How to make the Grid Panel for the Maze with border colors
        for (int i = 0; i < 25; i++) {
            //JPanel roomPanel = new JPanel();
            JPanel roomPanel = new JPanel(new BorderLayout());
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            roomPanel.setBackground(Color.WHITE);
            gridPanel.add(roomPanel);

            // This makes a Label for the Grid Panel to display numbers
            JLabel numberLabel = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            numberLabel.setFont(new Font("Arial", Font.BOLD, 30));
            roomPanel.add(numberLabel, BorderLayout.CENTER);

            /*
            JButton doorButton = new JButton("Door " + (i + 1));
            doorButton.setActionCommand(String.valueOf(i));
            doorButton.addActionListener(e -> handleDoorAction(Integer.parseInt(e.getActionCommand())));
             */

            gridPanel.add(roomPanel);
        }
        // The location of where the grid panel goes
        JPanel bottomLeftPanel = new JPanel(new BorderLayout());
        bottomLeftPanel.add(gridPanel, BorderLayout.NORTH);
        bottomLeftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(bottomLeftPanel, BorderLayout.EAST);


        room = new Room();

        /*
        // Load the image
        URL imageUrl = getClass().getClassLoader().getResource("views/fiveDoors.png");
        if (imageUrl == null) {
            System.out.println("Image not found");
        } else {
            ImageIcon originalImage = new ImageIcon(imageUrl);
            // Resize the image to 1100x800
            backgroundImage = originalImage.getImage().getScaledInstance(1100, 800, Image.SCALE_SMOOTH);
        }
        */

        //setLayout(null);

        // Initialize the door buttons
        initializeDoorButtons();

        // Add the door buttons to the panel
        for (JButton doorButton : doorButtons) {
            add(doorButton);
        }
    }

    private void initializeDoorButtons() {
        Door[] doors = room.getDoors();
        doorButtons = new JButton[doors.length];
        for (int i = 0; i < doors.length; i++) {
            doorButtons[i] = new JButton();
            doorButtons[i].setBounds(getDoorBounds(i));
            doorButtons[i].setContentAreaFilled(true); // Make the button invisible
            doorButtons[i].setBorderPainted(true); // Remove the border
            doorButtons[i].setFocusPainted(true); // Remove the focus outline
            doorButtons[i].setActionCommand(String.valueOf(i)); // Set action command to door index
            doorButtons[i].addActionListener(e -> handleDoorAction(Integer.parseInt(e.getActionCommand())));
        }
    }

    private void handleDoorAction(int doorIndex) {
        Door door = room.getDoors()[doorIndex];
        if (door.isLocked()) {
            JOptionPane.showMessageDialog(null, "This door is locked.");
        } else {
            // Ask a question
            boolean correctAnswer = door.askQuestion();
            if (correctAnswer) {// Unlock the door and enter a new room
                door.enterNewRoom();
            } else {
                door.lock(); // Lock the door permanently
                doorButtons[doorIndex].setEnabled(false); // Disable the button to indicate it's locked
                JOptionPane.showMessageDialog(null, "Incorrect! This door is now locked.");
            }
        }
    }

    private Rectangle getDoorBounds(int doorIndex) {
        // Return the bounds for each door button
        switch (doorIndex) {
            case 0: return new Rectangle(10, 150, 150, 310);
            case 1: return new Rectangle(260, 150, 150, 310);
            case 2: return new Rectangle(515, 150, 150, 310);
            case 3: return new Rectangle(760, 150, 150, 310);
            case 4: return new Rectangle(1012, 150, 150, 310);
            default: return new Rectangle();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);  // Draw the background image
        }
    }
}
