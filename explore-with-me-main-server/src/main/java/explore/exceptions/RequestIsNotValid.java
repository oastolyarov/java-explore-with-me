package explore.exceptions;

public class RequestIsNotValid extends RuntimeException {
    public RequestIsNotValid(String message) {
        super(message);
    }
}
