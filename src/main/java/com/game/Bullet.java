package com.game;

import java.awt.*;

class Bullet extends GameObject implements Movable{

    private final int speed = 10;

    public Bullet(int x, int y) {
        super(x, y, 5, 10);
    }
    @Override
    public void move() {
        y -= speed;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    public int getY() {
        return y;
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
}
