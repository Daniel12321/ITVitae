package nl.itvitae.foo.exception;

import nl.itvitae.foo.util.LineType;

public class InvalidDirectionException extends InvalidCommandException {

    private static final String MESSAGE = LineType.ERROR.makeLine("Invalid direction!");

    public InvalidDirectionException() {
        super(MESSAGE);
    }
}
