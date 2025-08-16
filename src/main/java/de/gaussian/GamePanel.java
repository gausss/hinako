package de.gaussian;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import de.gaussian.entity.Player;

public class GamePanel extends JPanel implements Runnable {
    // Screen
    static final int ORIGINAL_TILE_SIZE = 64;
    static final int SCALE = 1;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    static final int MAX_SCREEN_COLUMN = 16;
    static final int MAX_SCREEN_ROW = 12;
    static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    static final int DESIRED_FPS = 60;

    private long currentFPS;

    Thread gameThread;
    KeyboardControls keyboardControls;
    Player player;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        keyboardControls = new KeyboardControls();
        player = new Player(this, keyboardControls);

        this.addKeyListener(keyboardControls);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double frameTime = 1000000000 / DESIRED_FPS; // frame time 16ms
        double delta = 0;
        long lastTime = System.nanoTime();
        long currenTime;
        long timer = 0;
        currentFPS = 0;

        while (gameThread != null) {
            currenTime = System.nanoTime();
            long passedTime = currenTime - lastTime;
            delta += passedTime / frameTime;
            timer += passedTime;
            lastTime = currenTime;

            if (delta > 1) {
                // 1. Update state e.g. character position
                update();
                // 2. Draw the screen with updated state
                repaint();
                delta--;
                currentFPS++;
            }

            if (timer >= 1000000000) {
                System.out.println(currentFPS);
                timer = 0;
                currentFPS = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D panel = (Graphics2D) g;
        player.draw(panel);
        panel.dispose();
    }

}