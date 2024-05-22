import maze.Maze;

public class testMaze {

    public static void main(String[] args) {
        Maze maze = new Maze();

        // Test navigation
        System.out.println("Current room: " + maze.getCurrentRoomNumber());

        // Move to Room 2 (should ask a question)
        testMove(maze, "RIGHT");

        // Move to Room 7 (should ask a question)
        testMove(maze, "DOWN");

        // Move to Room 8 (should ask a question)
        testMove(maze, "RIGHT");

        // Move to Room 9 (should ask a question)
        testMove(maze, "RIGHT");

        // Move to Room 14 (should ask a question)
        testMove(maze, "DOWN");

        // Move back to Room 9 (should not ask a question)
        testMove(maze, "UP");

        // Move to Room 10 (should ask a question)
        testMove(maze, "RIGHT");

        // Invalid move (outside the maze)
        testMove(maze, "RIGHT");

        // Move back to Room 9 (should not ask a question)
        testMove(maze, "LEFT");

        // Move back to Room 8 (should not ask a question)
        testMove(maze, "LEFT");

        // Move to Room 13 (should ask a question)
        testMove(maze, "DOWN");

        // Move to Room 18 (should ask a question)
        testMove(maze, "DOWN");

        // Move to Room 23 (should ask a question)
        testMove(maze, "DOWN");

        // Move to Room 24 (should ask a question)
        testMove(maze, "RIGHT");

        // Move to Room 25 (should ask a question)
        testMove(maze, "RIGHT");

        if (maze.isAtExit()) {
            System.out.println("Successfully reached the exit room 25!");
        } else {
            System.out.println("Failed to reach the exit room 25.");
        }
    }

    private static void testMove(Maze maze, String direction) {
        System.out.println("Moving " + direction + " from room " + maze.getCurrentRoomNumber());
        boolean success = maze.move(direction);
        if (success) {
            System.out.println("Moved successfully to room " + maze.getCurrentRoomNumber());
        } else {
            System.out.println("Failed to move.");
        }
    }
}
