package de.gaussian.entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public int spriteNum;
    public int spriteCounter;

    public BufferedImage[] up, down, left, right;
    public EntityDirection direction;

    public enum EntityDirection {
        UP, DOWN, LEFT, RIGHT,
    }
}
