package explore.exceptions;

public class UserIdNotValidException extends RuntimeException {
    public UserIdNotValidException(String message) {
        super(message);
    }
}
