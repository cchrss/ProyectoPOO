package com.game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BulletTest {
    @Test
    public void testInitialPosition(){
        Bullet bullet = new Bullet(400,500);
        assertEquals(400, bullet.getX());
        assertEquals(500, bullet.getY());
    }
    @Test
    public void testMove(){
        Bullet bullet = new Bullet(400,500);
        int initialY = bullet.getY();
        bullet.move();
        assertTrue(bullet.getY()< initialY);
    }

}