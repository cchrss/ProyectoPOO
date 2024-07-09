package com.game;

public class InvalidPlayerNameException extends RuntimeException {
    public InvalidPlayerNameException(String playerNameCannotBeEmpty) {
        super(playerNameCannotBeEmpty);
    }
    public InvalidPlayerNameException(String e, Throwable cause) {
        super(e, cause);
    }
}
