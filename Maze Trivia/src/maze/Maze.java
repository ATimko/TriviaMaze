package maze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Maze {
    private Room[][] grid;
    private Map<String, Integer[]> roomDirections;
    private int currentRoomRow;
    private int currentRoomCol;
    private Set<String> visitedDoors;
    private int previousRoomNumber;

    public Maze() {
        grid = new Room[5][5];
        visitedDoors = new HashSet<>();
        initializeRooms();
        initializeRoomDirections();
        currentRoomRow = 0;
        currentRoomCol = 0;
        previousRoomNumber = -1; // Initialize to an invalid room number
    }

    private void initializeRooms() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Room();
            }
        }
    }

    private void initializeRoomDirections() {
        roomDirections = new HashMap<>();
        roomDirections.put("UP", new Integer[]{-1, 0});
        roomDirections.put("DOWN", new Integer[]{1, 0});
        roomDirections.put("LEFT", new Integer[]{0, -1});
        roomDirections.put("RIGHT", new Integer[]{0, 1});
    }

    public boolean move(int roomNumber) {
        Integer[] move = getDirectionFromRoomNumber(roomNumber);
        int newRow = currentRoomRow + move[0];
        int newCol = currentRoomCol + move[1];

        if (isValidMove(newRow, newCol)) {
            String forwardKey = getCurrentRoomNumber() + "-" + roomNumber;
            String backwardKey = roomNumber + "-" + getCurrentRoomNumber();
            Door door = getDoor(move);
            if (door.isLocked()) {
                System.out.println("This door is locked.");
                return false;
            } else if (visitedDoors.contains(forwardKey) || visitedDoors.contains(backwardKey) || roomNumber == previousRoomNumber || door.askQuestion()) {
                previousRoomNumber = getCurrentRoomNumber();
                currentRoomRow = newRow;
                currentRoomCol = newCol;
                visitedDoors.add(forwardKey);
                visitedDoors.add(backwardKey); // For the reverse path
                door.markVisited(); // Mark the door as visited
                //enterNewRoom();
                return true;
            } else {
                System.out.println("Incorrect! This door is now locked.");
                return false;
            }
        } else {
            System.out.println("Invalid move.");
            return false;
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }

    private Door getDoor(Integer[] move) {
        Room currentRoom = grid[currentRoomRow][currentRoomCol];
        if (move[0] == -1 && move[1] == 0) return currentRoom.getDoors()[0]; // UP
        if (move[0] == 1 && move[1] == 0) return currentRoom.getDoors()[1]; // DOWN
        if (move[0] == 0 && move[1] == -1) return currentRoom.getDoors()[2]; // LEFT
        if (move[0] == 0 && move[1] == 1) return currentRoom.getDoors()[3]; // RIGHT
        throw new IllegalArgumentException("Invalid move");
    }

    private Integer[] getDirectionFromRoomNumber(int roomNumber) {
        int currentRoomNumber = getCurrentRoomNumber();
        if (roomNumber == currentRoomNumber - 5) return new Integer[]{-1, 0}; // UP
        if (roomNumber == currentRoomNumber + 5) return new Integer[]{1, 0}; // DOWN
        if (roomNumber == currentRoomNumber - 1) return new Integer[]{0, -1}; // LEFT
        if (roomNumber == currentRoomNumber + 1) return new Integer[]{0, 1}; // RIGHT
        throw new IllegalArgumentException("Invalid room number");
    }

    public int getCurrentRoomNumber() {
        return currentRoomRow * 5 + currentRoomCol + 1;
    }

    public boolean isAtExit() {
        return currentRoomRow == 4 && currentRoomCol == 4;
    }

    public void displayAvailableMoves() {
        System.out.println("You are in room " + getCurrentRoomNumber() + ". Available moves:");
        if (currentRoomRow > 0) System.out.println("UP to room " + (getCurrentRoomNumber() - 5));
        if (currentRoomRow < 4) System.out.println("DOWN to room " + (getCurrentRoomNumber() + 5));
        if (currentRoomCol > 0) System.out.println("LEFT to room " + (getCurrentRoomNumber() - 1));
        if (currentRoomCol < 4) System.out.println("RIGHT to room " + (getCurrentRoomNumber() + 1));
    }

    public boolean isPathToEnd() {
        boolean[][] visited = new boolean[5][5];
        return dfs(0, 0, visited); // Start from room 1 (coordinates 0, 0)
    }

    private boolean dfs(int row, int col, boolean[][] visited) {
        if (row == 4 && col == 4) {
            return true; // Reached room 25
        }
        if (row < 0 || col < 0 || row >= 5 || col >= 5 || visited[row][col]) {
            return false;
        }

        visited[row][col] = true;

        for (String direction : roomDirections.keySet()) {
            Integer[] move = roomDirections.get(direction);
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (isDirectionAccessible(row, col, direction) && dfs(newRow, newCol, visited)) return true;
        }

        visited[row][col] = false; // Unmark the node to allow backtracking
        return false;
    }

    private boolean isDirectionAccessible(int row, int col, String direction) {
        Room currentRoom = grid[row][col];
        int doorIndex = -1;
        switch (direction) {
            case "UP":
                doorIndex = 0;
                break;
            case "DOWN":
                doorIndex = 1;
                break;
            case "LEFT":
                doorIndex = 2;
                break;
            case "RIGHT":
                doorIndex = 3;
                break;
        }
        Door door = currentRoom.getDoors()[doorIndex];
        return door.isUnlocked();
    }

    private void enterNewRoom() {
        // Add any additional logic for entering a new room here
    }
}
