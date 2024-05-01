import java.util.Random;

public class Maze {
    private Room[][] rooms;
    private int size;

    public Maze(int size) {
        this.size = size;
        this.rooms = new Room[size][size];
    }

    public void setupMaze() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rooms[i][j] = new Room("Room_" + i + "_" + j);
                if (i > 0) {
                    rooms[i][j].addDoor(new Door(rooms[i - 1][j], generateQuestion()));
                }
                if (j > 0) {
                    rooms[i][j].addDoor(new Door(rooms[i][j - 1], generateQuestion()));
                }
            }
        }
    }

    private Question generateQuestion() {
        // Code to fetch question from SQLite database
        // Here we'll just create a sample question
        String question = "Sample question?";
        String[] options = {"Option A", "Option B", "Option C", "Option D"};
        String correctAnswer = "Option A";
        String type = "multiple_choice";
        return new Question(question, options, correctAnswer, type);
    }

    public double[][] getRooms() {
        return new double[0][];
    }
}