package com.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

class SpaceShip extends GameObject implements Movable{
    private int dx, dy;
    private int y;
    private BufferedImage image;


    public SpaceShip() {
        super(375,500,50,50);
        x=375;
        y=500;
        width=50;
        height=50;
        dx=0;
        dy=0;
        loadImage();
    }
    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/spaceship.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void move() {
        x += dx;
        y += dy;
        if (x < 0) x = 0;
        if (x > 750) x = 750;
        if (y < 0) y = 0;
        if (y > 550) y = 550;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) dx = -5;
        if (key == KeyEvent.VK_RIGHT) dx = 5;
        if (key == KeyEvent.VK_UP) dy = -5;
        if (key == KeyEvent.VK_DOWN) dy = 5;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) dx = 0;
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) dy = 0;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image,x,y,width,height,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
