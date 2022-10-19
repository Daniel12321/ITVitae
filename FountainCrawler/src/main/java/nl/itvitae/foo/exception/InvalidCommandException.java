package nl.itvitae.foo.exception;

import nl.itvitae.foo.util.LineType;

public class InvalidCommandException extends Exception {

    private static final String MESSAGE = LineType.ERROR.makeLine("Invalid command!");

    public InvalidCommandException() {
        super(MESSAGE);
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
