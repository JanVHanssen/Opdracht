package Opdracht.Opdracht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CampusNotFoundException extends RuntimeException {
    public CampusNotFoundException(String message) {
        super(message);
    }
}
