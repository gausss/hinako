package de.gaussian;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private static final int ORIGINAL_TILE_SIZE = 64;
    private static final int SCALE = 1;
    private static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    private static final int MAX_SCREEN_COLUMN = 16;
    private static final int MAX_SCREEN_ROW = 12;
    private static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    private static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

}