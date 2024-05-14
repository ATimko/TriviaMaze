package Common;

import java.awt.*;
import java.awt.Dimension;

// Contains all constants and static values.
public class mazeConstants {

    /**
     * Private constructor so this class can't be instantiated.
     */
    private mazeConstants() {

    }
    public static final class Dimensions {
        /**
         * Height of the window.
         */
        public static final int WINDOW_HEIGHT = 700;

        /**
         * Width of the window.
         */
        public static final int WINDOW_WIDTH = 1000;

        public static final Dimension ABOUT_PANEL_DIMENSION = new Dimension(700, 700);
    }
}