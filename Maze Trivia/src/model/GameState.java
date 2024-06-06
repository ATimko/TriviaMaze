package model;

import java.io.Serializable;
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
}
