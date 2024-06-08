package model;

import java.io.*;
import java.util.List;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private Maze maze;
    private int score;
    private int playerPositionX;
    private int playerPositionY;
    private int currentRoom;
    private List<String> questionsAnswered;

    public GameState(Maze maze, int score, int playerPositionX, int playerPositionY, int currentRoom, List<String> questionsAnswered) {
        this.maze = maze;
        this.score = score;
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        this.currentRoom = currentRoom;
        this.questionsAnswered = questionsAnswered;
    }
    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayerPositionX() {
        return playerPositionX;
    }

    public void setPlayerPositionX(int playerPositionX) {
        this.playerPositionX = playerPositionX;
    }

    public int getPlayerPositionY() {
        return playerPositionY;
    }

    public void setPlayerPositionY(int playerPositionY) {
        this.playerPositionY = playerPositionY;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<String> getQuestionsAnswered() {
        return questionsAnswered;
    }

    public void setQuestionsAnswered(List<String> questionsAnswered) {
        this.questionsAnswered = questionsAnswered;
    }

    public static GameState loadGame(String fileName) {
        GameState gameState = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            gameState = (GameState) in.readObject();
            System.out.println("Game state loaded from " + fileName);
            return gameState;
        }
        catch (IOException i) {
            i.printStackTrace();
        }
        catch (ClassNotFoundException c) {
            System.out.println("GameState class not found"  + c.getMessage());
            c.printStackTrace();
            return null;
        }
        return gameState;
    }

    public static void saveGame(GameState gameState, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(gameState);
            System.out.println("Game state saved to " + fileName);
        }
        /*
        catch (IOException i) {
            i.printStackTrace();
        }
         */
        catch (IOException e) {
            System.err.println("Error saving game state: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
