package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.stream.Collectors;


public class SpaceShooter extends JPanel implements ActionListener {

    ByteArrayOutputStream getBullets;
    private String playerName;
    private Timer timer;
    private SpaceShip ship;
    private List<Asteroid> asteroids;
    List<Bullet> bullets;
    private int score;

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS scores (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER)";
    private static final String INSERT_SCORE_QUERY = "INSERT INTO scores (name, score) VALUES (?, ?)";
    private static final String SELECT_TOP_SCORES_QUERY = "SELECT name, score FROM scores ORDER BY score DESC LIMIT 10";

    public SpaceShooter() {
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        ship = new SpaceShip();
        asteroids = new ArrayList<>();
        bullets = new ArrayList<>();
        score = 0;
        playerName = "";
        timer = new Timer(20, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                ship.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    shootBullet();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ship.keyReleased(e);
            }
        });

        initializeDatabase();
        loadTopScores();
    }

    public void shootBullet() {
        int bulletX = ship.getX() + ship.getWidth() / 2;
        int bulletY = ship.getY();
        bullets.add(new Bullet(bulletX, bulletY));
    }

    public void initializeDatabase() {
        try (Connection conn = Database.getConnection();Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            throw new DatabaseException("Error initiazlizing database", e);
        }
    }
    public void saveScore(String name, int score) {
        if(name == null || name.trim().isEmpty()){
            throw new InvalidPlayerNameException("Player name cannot be empty");
        }
        try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_SCORE_QUERY)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error saving score", e);

        }
    }
    private void checkCollisions() {
        List<Bullet> bulletsToRemove = bullets.stream()
                        .filter(bullet -> asteroids.stream()
                        .anyMatch(asteroid -> asteroid.getBounds().intersects(bullet.getBounds())))
                        .collect(Collectors.toList());
        List<Asteroid> asteroidsToRemove = asteroids.stream()
                        .filter(asteroid -> bulletsToRemove.stream()
                        .anyMatch(bullet -> asteroid.getBounds().intersects(bullet.getBounds())))
                        .collect(Collectors.toList());
        bullets.removeAll(bulletsToRemove);
        asteroids.removeAll(asteroidsToRemove);
        score +=10*asteroidsToRemove.size();
        if (asteroids.stream().anyMatch(asteroid->asteroid.getBounds().intersects(ship.getBounds()))){
            timer.stop();
            playerName = JOptionPane.showInputDialog(this, "Game Over! Enter your name:");
            saveScore(playerName, score);
            JOptionPane.showMessageDialog(this, "Game Over! Your score is: " + score);
            throw new GameOverException("Game Over"+score);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ship.move();
        moveBullet();
        moveAsteroids();
        checkCollisions();
        spawnAsteroids();
        repaint();
    }

    public void loadTopScores() {
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_TOP_SCORES_QUERY);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("Top 10 Scores:");
            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                System.out.println(name + ": " + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void spawnAsteroids() {
        if (Math.random() < 0.02) {
            asteroids.add(new Asteroid(getWidth()));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
        asteroids.forEach(asteroid -> asteroid.draw(g));
        bullets.forEach(bullet -> bullet.draw(g));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    private void moveBullet() {
        bullets.forEach(Bullet::move);
        bullets.removeIf(bullet -> bullet.getY() < 0);
    }

    private void moveAsteroids() {
        asteroids.forEach(Asteroid::move);
        asteroids.removeIf(asteroid -> asteroid.getY() > getHeight());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Space Shooter");
            SpaceShooter game = new SpaceShooter();
            frame.add(game);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
