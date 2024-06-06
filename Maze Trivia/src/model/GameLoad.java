package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameLoad {
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
}
