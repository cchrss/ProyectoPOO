package com.game;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceShipTest {

    @Test
    public void testMove(){
        SpaceShip ship = new SpaceShip();
        ship.keyPressed(new KeyEvent(new JButton(),0,0,0,KeyEvent.VK_LEFT , 'U'));
        ship.move();
        assertEquals(495, ship.getX());
    }

}