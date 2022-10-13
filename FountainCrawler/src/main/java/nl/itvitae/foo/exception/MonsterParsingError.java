package nl.itvitae.foo.exception;

public class MonsterParsingError extends Error {

    public MonsterParsingError(Exception exc) {
        super(exc);
    }
}
