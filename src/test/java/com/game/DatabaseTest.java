package com.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {
    @Test
    public void testConnection(){
        try(Connection connection = Database.getConnection()) {
            assertNotNull(connection);
        } catch (SQLException e) {
            fail("Database connection failed.");
        }
    }

}