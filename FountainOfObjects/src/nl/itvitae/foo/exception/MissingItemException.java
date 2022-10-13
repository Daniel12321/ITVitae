package nl.itvitae.foo.exception;

public class MissingItemException extends InvalidCommandException {

    public MissingItemException(String message) {
        super(message);
    }
}
