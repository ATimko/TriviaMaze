package Maze;

public class room {
    private Door[] doors;

    public room() {
        doors = new Door[5];
        initializeDoors();
    }

    private void initializeDoors() {
        for (int i = 0; i < doors.length; i++) {
            doors[i] = new Door();
        }
    }

    public Door[] getDoors() {
        return doors;
    }
}
