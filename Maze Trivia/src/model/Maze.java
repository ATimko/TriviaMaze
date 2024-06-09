package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Maze implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Room[][] grid;
    private final Map<String, Integer[]> roomDirections;
    private int currentRoomRow;
    private int currentRoomCol;
    private final Set<String> visitedDoors;
    private final Set<String> correctlyAnsweredDoors;
    private Question newQuestion;

    /**
     * Constructs a Maze object with the specified grid and room directions.
     *
     * @param grid           The 2D array representing the maze grid.
     * @param roomDirections The map of room directions.
     */
    public Maze(Room[][] grid, Map<String, Integer[]> roomDirections) {
        this.grid = grid;
        this.roomDirections = roomDirections;
        this.visitedDoors = new HashSet<>();
        this.correctlyAnsweredDoors = new HashSet<>();
        this.currentRoomRow = 0;
        this.currentRoomCol = 0;
        newQuestion = QuestionFactory.getRandomQuestion();
    }

    /**
     * Attempts to move to the specified room number.
     *
     * @param roomNumber The room number to move to.
     * @return true if the move is valid and the room was previously visited or answered correctly, false otherwise.
     */
    public boolean attemptMove(int roomNumber) {
        Integer[] move = getDirectionFromRoomNumber(roomNumber);
        int newRow = currentRoomRow + move[0];
        int newCol = currentRoomCol + move[1];

        if (isValidMove(newRow, newCol)) {
            String forwardKey = getCurrentRoomNumber() + "-" + roomNumber;
            String backwardKey = roomNumber + "-" + getCurrentRoomNumber();

            if (correctlyAnsweredDoors.contains(forwardKey) || correctlyAnsweredDoors.contains(backwardKey)) {
                return true;
            } else {
                newQuestion = QuestionFactory.getRandomQuestion();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Moves to the specified room number if the answer is correct.
     *
     * @param roomNumber      The room number to move to.
     * @param isAnswerCorrect Indicates whether the answer is correct.
     */
    public void move(int roomNumber, boolean isAnswerCorrect) {
        if (!isAnswerCorrect) {
            return; // Answer was incorrect, do not move
        }

        Integer[] move = getDirectionFromRoomNumber(roomNumber);
        int newRow = currentRoomRow + move[0];
        int newCol = currentRoomCol + move[1];

        if (isValidMove(newRow, newCol)) {
            String forwardKey = getCurrentRoomNumber() + "-" + roomNumber;
            String backwardKey = roomNumber + "-" + getCurrentRoomNumber();

            currentRoomRow = newRow;
            currentRoomCol = newCol;
            visitedDoors.add(forwardKey);
            visitedDoors.add(backwardKey);
            correctlyAnsweredDoors.add(forwardKey);
            newQuestion = QuestionFactory.getRandomQuestion(); // Generate new question
        }
    }

    /**
     * Checks if the specified move is valid within the maze grid.
     *
     * @param row The row index of the move.
     * @param col The column index of the move.
     * @return true if the move is valid, false otherwise.
     */
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }

    /**
     * Gets the door corresponding to the specified move.
     *
     * @param move The array representing the move direction.
     * @return The Door object corresponding to the move.
     */
    private Door getDoor(Integer[] move) {
        Room currentRoom = grid[currentRoomRow][currentRoomCol];
        if (move[0] == -1 && move[1] == 0) return currentRoom.getDoors()[0]; // UP
        if (move[0] == 1 && move[1] == 0) return currentRoom.getDoors()[1]; // DOWN
        if (move[0] == 0 && move[1] == -1) return currentRoom.getDoors()[2]; // LEFT
        if (move[0] == 0 && move[1] == 1) return currentRoom.getDoors()[3]; // RIGHT
        throw new IllegalArgumentException("Invalid move");
    }

    /**
     * Gets the direction from the current room number to the specified room number.
     *
     * @param roomNumber The room number to get the direction to.
     * @return The array representing the direction.
     */
    public Integer[] getDirectionFromRoomNumber(int roomNumber) {
        int currentRoomNumber = getCurrentRoomNumber();
        if (roomNumber == currentRoomNumber - 5) return new Integer[]{-1, 0}; // UP
        if (roomNumber == currentRoomNumber + 5) return new Integer[]{1, 0}; // DOWN
        if (roomNumber == currentRoomNumber - 1) return new Integer[]{0, -1}; // LEFT
        if (roomNumber == currentRoomNumber + 1) return new Integer[]{0, 1}; // RIGHT
        throw new IllegalArgumentException("Invalid room number");
    }

    /**
     * Gets the current room number.
     *
     * @return The current room number.
     */
    public int getCurrentRoomNumber() {
        return currentRoomRow * 5 + currentRoomCol + 1;
    }

    /**
     * Gets the current question.
     *
     * @return The current Question object.
     */
    public Question getCurrentQuestion() {
        return newQuestion;
    }

    /**
     * Checks if the current room is the exit room.
     *
     * @return true if the current room is the exit room, false otherwise.
     */
    public boolean isAtExit() {
        return currentRoomRow == 4 && currentRoomCol == 4;
    }

    /**
     * Checks if there is a path to the end of the maze.
     *
     * @return true if there is a path to the end, false otherwise.
     */
    public boolean isPathToEnd() {
        boolean[][] visited = new boolean[5][5];
        return dfs(0, 0, visited); // Start from room 1 (coordinates 0, 0)
    }

    /**
     * Depth-First Search (DFS) to check for a path to the end of the maze.
     *
     * @param row     The current row index.
     * @param col     The current column index.
     * @param visited The 2D array tracking visited rooms.
     * @return true if a path to the end is found, false otherwise.
     */
    private boolean dfs(int row, int col, boolean[][] visited) {
        if (row == 4 && col == 4) {
            return true;
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

        visited[row][col] = false;
        return false;
    }

    /**
     * Checks if the specified direction is accessible from the current room.
     *
     * @param row       The current row index.
     * @param col       The current column index.
     * @param direction The direction to check.
     * @return true if the direction is accessible, false otherwise.
     */
    private boolean isDirectionAccessible(int row, int col, String direction) {
        Room currentRoom = grid[row][col];
        int doorIndex = switch (direction) {
            case "UP" -> 0;
            case "DOWN" -> 1;
            case "LEFT" -> 2;
            case "RIGHT" -> 3;
            default -> throw new IllegalArgumentException("Invalid direction");
        };
        Door door = currentRoom.getDoors()[doorIndex];
        return door.isUnlocked();
    }

    /**
     * Checks if the player can move up from the current room.
     *
     * @return true if the player can move up, false otherwise.
     */
    public boolean canMoveUp() {
        return currentRoomRow > 0 && (!isDirectionBlocked("UP") || isVisitedDirection("UP"));
    }

    /**
     * Checks if the player can move down from the current room.
     *
     * @return true if the player can move down, false otherwise.
     */
    public boolean canMoveDown() {
        return currentRoomRow < 4 && (!isDirectionBlocked("DOWN") || isVisitedDirection("DOWN"));
    }

    /**
     * Checks if the player can move left from the current room.
     *
     * @return true if the player can move left, false otherwise.
     */
    public boolean canMoveLeft() {
        return currentRoomCol > 0 && (!isDirectionBlocked("LEFT") || isVisitedDirection("LEFT"));
    }

    /**
     * Checks if the player can move right from the current room.
     *
     * @return true if the player can move right, false otherwise.
     */
    public boolean canMoveRight() {
        return currentRoomCol < 4 && (!isDirectionBlocked("RIGHT") || isVisitedDirection("RIGHT"));
    }

    /**
     * Checks if the specified direction has been visited from the current room.
     *
     * @param direction The direction to check.
     * @return true if the direction has been visited, false otherwise.
     */
    public boolean isVisitedDirection(String direction) {
        Integer[] move = roomDirections.get(direction);
        int newRow = currentRoomRow + move[0];
        int newCol = currentRoomCol + move[1];
        int newRoomNumber = (newRow * 5) + newCol + 1;
        String forwardKey = getCurrentRoomNumber() + "-" + newRoomNumber;
        String backwardKey = newRoomNumber + "-" + getCurrentRoomNumber();
        return visitedDoors.contains(forwardKey) || visitedDoors.contains(backwardKey);
    }

    /**
     * Checks if the specified direction is blocked from the current room.
     *
     * @param direction The direction to check.
     * @return true if the direction is blocked, false otherwise.
     */
    public boolean isDirectionBlocked(String direction) {
        Integer[] move = roomDirections.get(direction);
        int newRow = currentRoomRow + move[0];
        int newCol = currentRoomCol + move[1];
        int newRoomNumber = (newRow * 5) + newCol + 1;
        String forwardKey = getCurrentRoomNumber() + "-" + newRoomNumber;
        String backwardKey = newRoomNumber + "-" + getCurrentRoomNumber();
        return (visitedDoors.contains(forwardKey) && !correctlyAnsweredDoors.contains(forwardKey)) || (visitedDoors.contains(backwardKey) && !correctlyAnsweredDoors.contains(backwardKey));
    }

    /**
     * Locks the door in the specified direction from the current room.
     *
     * @param direction The direction to lock the door.
     */
    public void lockCurrentDoor(String direction) {
        Integer[] move = roomDirections.get(direction);
        Door door = getDoor(move);
        door.lock(); // Lock the door
        String forwardKey = getCurrentRoomNumber() + "-" + getRoomNumberFromMove(move);
        visitedDoors.add(forwardKey);
    }

    /**
     * Gets the room number from the specified move direction.
     *
     * @param move The array representing the move direction.
     * @return The room number corresponding to the move direction.
     */
    private int getRoomNumberFromMove(Integer[] move) {
        int newRow = currentRoomRow + move[0];
        int newCol = currentRoomCol + move[1];
        return (newRow * 5) + newCol + 1;
    }

    /**
     * Gets the target room number for the specified direction.
     *
     * @param direction The direction to get the target room number.
     * @return The target room number.
     */
    public int getTargetRoomNumber(String direction) {
        int currentRoomNumber = getCurrentRoomNumber();
        return switch (direction) {
            case "UP" -> currentRoomNumber - 5;
            case "DOWN" -> currentRoomNumber + 5;
            case "LEFT" -> currentRoomNumber - 1;
            case "RIGHT" -> currentRoomNumber + 1;
            default -> throw new IllegalArgumentException("Invalid direction");
        };
    }
}
