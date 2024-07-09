package com.game;

import java.awt.*;
import java.util.Random;

class Asteroid extends GameObject implements Movable{

    public Asteroid(int gameWidth) {
        super ((int)(Math.random() * gameWidth-50), 0, 50, 50);
    }
    
    @Override
    public void move() {
        y += 2;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillOval(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
