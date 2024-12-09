package io.devandre.furtmates.shared.exception;

public class UserDisabledException extends RuntimeException {
    public UserDisabledException(String message) {
        super(message);
    }
}
