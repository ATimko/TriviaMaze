package game;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private int playerPositionX;
    private int playerPositionY;
    private int currentRoom;
    private List<String> questionsAnswered;

    public GameState(int playerPositionX, int playerPositionY, int currentRoom, List<String> questionsAnswered) {
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        this.currentRoom = currentRoom;
        this.questionsAnswered = questionsAnswered;
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
