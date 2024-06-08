package model;

public class Door {
    private boolean locked;

    public Door() {
        this.locked = false; // Initially, the door is unlocked
    }

    public boolean isUnlocked() {
        return !locked;
    }

    public void lock() {
        this.locked = true;
    }

}
