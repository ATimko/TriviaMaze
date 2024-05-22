package maze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Maze {
    private Room[][] grid;
    private Set<Integer> visitedRooms;
    private Map<String, Integer[]> roomDirections;
    private int currentRoomRow;
    private int currentRoomCol;

    public Maze() {
        grid = new Room[5][5];
        visitedRooms = new HashSet<>();
        initializeRooms();
        initializeRoomDirections();
        currentRoomRow = 0;
        currentRoomCol = 0;
        visitedRooms.add(getCurrentRoomNumber()); // Mark the starting room as visited
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

    public boolean move(String direction) {
        Integer[] move = roomDirections.get(direction);
        int newRow = currentRoomRow + move[0];
        int newCol = currentRoomCol + move[1];

        if (isValidMove(newRow, newCol)) {
            Door door = getDoor(direction);
            if (door.isLocked()) {
                System.out.println("This door is locked.");
                return false;
            } else if (door.isUnlocked() || door.askQuestion()) {
                if (!door.isUnlocked()) {
                    door.unlock();
                }
                currentRoomRow = newRow;
                currentRoomCol = newCol;
                visitedRooms.add(getCurrentRoomNumber());
                System.out.println("Moved to room: " + getCurrentRoomNumber());
                return true;
            } else {
                door.lock();
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

    private Door getDoor(String direction) {
        Room currentRoom = grid[currentRoomRow][currentRoomCol];
        switch (direction) {
            case "UP":
                return currentRoom.getDoors()[0];
            case "DOWN":
                return currentRoom.getDoors()[1];
            case "LEFT":
                return currentRoom.getDoors()[2];
            case "RIGHT":
                return currentRoom.getDoors()[3];
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }

    public int getCurrentRoomNumber() {
        return currentRoomRow * 5 + currentRoomCol + 1;
    }

    public boolean isAtExit() {
        return currentRoomRow == 4 && currentRoomCol == 4;
    }
}
