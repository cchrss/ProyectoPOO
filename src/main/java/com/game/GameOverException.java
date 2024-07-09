package com.game;

public class GameOverException extends RuntimeException {
    public GameOverException(String message) {
        super(message);
    }
    public GameOverException(String message, Throwable cause) {
        super(message, cause);
    }
}
