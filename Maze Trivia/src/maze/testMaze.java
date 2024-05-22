import maze.Maze;

import java.util.Scanner;

public class testMaze {

    public static void main(String[] args) {
        Maze maze = new Maze();
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
        }

        System.out.println("Successfully reached the exit room 25!");
    }
}
