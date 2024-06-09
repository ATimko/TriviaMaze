package model;
import java.io.*;
public record GameState(Maze maze) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Saves the game state to a file.
     *
     * @param gameState The GameState object to be saved.
     * @param filePath  The file path where the game state will be saved.
     * @throws IOException If an I/O error occurs while saving the game state.
     */
    public static void saveGame(GameState gameState, String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(gameState);
        }
    }

    /**
     * Loads the game state from a file.
     *
     * @param filePath The file path from which the game state will be loaded.
     * @return The GameState object loaded from the file.
     * @throws IOException            If an I/O error occurs while loading the game state.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public static GameState loadGame(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GameState) in.readObject();
        }
    }
}
