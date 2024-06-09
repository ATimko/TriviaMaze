package model;

import java.io.*;


public record GameState(Maze maze) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static void saveGame(GameState gameState, String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(gameState);
        }
    }

    public static GameState loadGame(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GameState) in.readObject();
        }
    }
}
