package de.gaussian.entity;

import static de.gaussian.GamePanel.TILE_SIZE;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.gaussian.GamePanel;
import de.gaussian.KeyboardControls;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyboardControls keyboardControls;

    public Player(GamePanel gamePanel, KeyboardControls keyboardControls) {
        this.gamePanel = gamePanel;
        this.keyboardControls = keyboardControls;
        setDefaults();
        getImage();
    }

    private void setDefaults() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (keyboardControls.upPressed || keyboardControls.downPressed || keyboardControls.leftPressed
                || keyboardControls.rightPressed) {
            spriteCounter++;
        }

        if (keyboardControls.upPressed) {
            direction = EntityDirection.UP;
            y -= speed;
        }
        if (keyboardControls.downPressed) {
            direction = EntityDirection.DOWN;
            y += speed;
        }
        if (keyboardControls.leftPressed) {
            direction = EntityDirection.LEFT;
            x -= speed;
        }
        if (keyboardControls.rightPressed) {
            direction = EntityDirection.RIGHT;
            x += speed;
        }

        if (spriteCounter > 10) {
            if (spriteNum == 0) {
                spriteNum = 1;
            } else if (spriteNum == 1) {
                spriteNum = 0;
            }

            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D panel) {
        BufferedImage image = null;
        switch (direction) {
            case DOWN -> image = down[spriteNum];
            case LEFT -> image = left[spriteNum];
            case RIGHT -> image = right[spriteNum];
            case UP -> image = up[spriteNum];
        }
        panel.drawImage(image, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    public void getImage() {
        try {
            up = new BufferedImage[] {
                    ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon1r.jpg")),
                    ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon2r.jpg")) };
            down = new BufferedImage[] {
                    ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon1r.jpg")),
                    ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon2r.jpg")) };
            left = new BufferedImage[] {
                    ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon1l.jpg")),
                    ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon2l.jpg")) };
            right = new BufferedImage[] {
                    ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon1r.jpg")),
                    ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon2r.jpg")) };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
