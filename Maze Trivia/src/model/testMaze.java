package model;

import model.Maze;
import model.MazeFactory;
import java.util.Scanner;

public class testMaze {

    public static void main(String[] args) {
        Maze maze = MazeFactory.createMaze();
        Scanner scanner = new Scanner(System.in);

        while (!maze.isAtExit()) {
            System.out.println("Current room: " + maze.getCurrentRoomNumber());
            maze.displayAvailableMoves();

            System.out.print("Enter the room number to move to: ");
            int roomNumber = scanner.nextInt();

            if (maze.move(roomNumber)) {
                System.out.println("Moved successfully to room " + maze.getCurrentRoomNumber());
            } else {
                System.out.println("Failed to move.");
            }

            // Check if there is a path to room 25 after each move
            if (!maze.isPathToEnd()) {
                System.out.println("No path to room 25. Game over.");
                return;
            }
        }

        System.out.println("Successfully reached the exit room 25!");
    }
}
