package com.game;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class AdditionalTest {
    @Test
    public void testAsteroidBound(){
        Asteroid asteroid = new Asteroid(800);
        assertNotNull(asteroid.getBounds());
    }
    @Test
    public void testBulleMove(){
        Bullet bullet = new Bullet (400,300);
        int initialY = bullet.getY();
        bullet.move();
        assertTrue(bullet.getY() < initialY);
    }
    @Test
    public void testSpaceShipBounds(){
        SpaceShip ship = new SpaceShip();
        assertNotNull(ship.getBounds());
    }
    @Test
    public void testSpaceShipKeyRelease(){
        SpaceShip ship = new SpaceShip();
        ship.keyPressed(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_LEFT, 'L'));
        ship.keyReleased(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_LEFT, 'L'));
        ship.move();
        assertEquals(500, ship.getX());
    }
    @Test
    public void testDatabaseCreateTable(){
        try(Connection conn = Database.getConnection()){
            Database.createTables(conn);
            assertNotNull(conn);
        }catch (SQLException e){
            fail("Database table creation failed");
        }
    }
}
