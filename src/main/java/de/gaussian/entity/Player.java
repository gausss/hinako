package de.gaussian.entity;

import static de.gaussian.GamePanel.TILE_SIZE;

import java.awt.Color;
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
    }

    public void draw(Graphics2D panel) {
        BufferedImage image = null;
        switch (direction) {
            case DOWN -> image = down1;
            case LEFT -> image = left1;
            case RIGHT -> image = right1;
            case UP -> image = up1;
        }
        panel.drawImage(image, x, y, TILE_SIZE, TILE_SIZE, null);
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon1.jpg"));
            up2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon2.jpg"));
            down1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon1.jpg"));
            down2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon2.jpg"));
            left1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon1.jpg"));
            left2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon2.jpg"));
            right1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon1.jpg"));
            right2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("sprites/dragon2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
