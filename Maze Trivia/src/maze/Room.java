package maze;

public class Room {
    private Door[] doors;

    public Room() {
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
