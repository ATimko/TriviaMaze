// Class may or may not be used as it can be connected to other classes
// This connects with the main class for now and can be moved around later
public class TriviaMaze {
    private Maze maze;
    private GameState gameState;
    private Player player;

    public TriviaMaze() {
        maze = new Maze(4); // 4 At the moment
        gameState = new GameState();
        player = new Player("Player 1");
    }

    public void startGame() {
        System.out.println("Welcome to the Trivia Maze, " + player.getName() + "!");
        maze.setupMaze();
        // Set player's position
        // Can be moved later
        gameState.setCurrentRoom(maze.getRooms()[0][0]);
        navigate();
    }

    private void navigate() {

    }
}