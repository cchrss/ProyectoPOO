package com.game;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String errorSavingScore) {
        super(errorSavingScore);
    }
    public DatabaseException(String e, Throwable cause) {
        super(e, cause);
    }
}
