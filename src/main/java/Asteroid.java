import java.awt.*;
import java.util.Random;

class Asteroid {
    private int x, y;
    private final int width = 50, height = 50;
    private final int speed;

    public Asteroid(int screenWidth) {
        Random random = new Random();
        x = random.nextInt(screenWidth - width);
        y = 0;
        speed = random.nextInt(5) + 1;
    }

    public void move() {
        y += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getY() {
        return y;
    }
}
