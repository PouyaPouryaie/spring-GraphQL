package ir.bigz.spring.graphql.person.exception;

public class PersonQLException extends RuntimeException {

    public PersonQLException() {
        super();
    }

    public PersonQLException(String message) {
        super(message);
    }

    public PersonQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonQLException(Throwable cause) {
        super(cause);
    }
}
