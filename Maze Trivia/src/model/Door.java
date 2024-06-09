package model;

import java.io.Serial;
import java.io.Serializable;

public class Door implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private boolean locked;

    public Door() {
        this.locked = false;
    }

    public boolean isUnlocked() {
        return !locked;
    }

    public void lock() {
        this.locked = true;
    }

}
