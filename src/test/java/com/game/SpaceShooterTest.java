package com.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class SpaceShooterTest {
    @Test
    public void testMoveSpaceShip(){
        SpaceShip ship = new SpaceShip();
        ship.keyPressed(new KeyEvent(new JButton(),0,0,0,KeyEvent.VK_LEFT , 'L'));
        ship.move();
        assertEquals(370, ship.getX());
    }
    @Test
    public void testShootBullet(){
        SpaceShooter game = new SpaceShooter();
        int initialSize=game.getBullets.size();
        game.shootBullet();
        assertEquals(initialSize+1, game.bullets.size());
    }
}
