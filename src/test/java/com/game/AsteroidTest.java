package com.game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class AsteroidTest {
    @Test
    public void testInitialPosition(){
        Asteroid asteroid = new Asteroid(800);
        assertTrue(asteroid.getX() >= 0 && asteroid.getX() <= 750);
        assertEquals(0, asteroid.getY());
    }
    @Test
    public void testMove(){
        Asteroid asteroid = new Asteroid(800);
        int initialY = asteroid.getY();
        asteroid.move();
        assertTrue(asteroid.getY() > initialY);
    }
}