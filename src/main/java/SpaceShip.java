import java.awt.*;
import java.awt.event.KeyEvent;

class SpaceShip {
    private int x, y, dx, dy;
    private final int width = 50, height = 50;

    public SpaceShip() {
        x = 375;
        y = 500;
        dx = 0;
        dy = 0;
    }

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

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
