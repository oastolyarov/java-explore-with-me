package explore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerIsNotNull(final NullPointerException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(UserIdNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlerNoValid(final UserIdNotValidException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> itemNotValidHandler(final EventNotFoundException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(RequestIsNotValid.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> bookingNotValidHandler(final RequestIsNotValid e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(DataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> dataNotValid(final DataException e) {
        return Map.of("error", e.getMessage());
    }
}
