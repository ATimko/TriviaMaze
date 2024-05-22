package constants;

import java.awt.Dimension;

// Contains all constants and static values.
public class MazeConstants {

    /**
     * Private constructor so this class can't be instantiated.
     */
    private MazeConstants() {

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