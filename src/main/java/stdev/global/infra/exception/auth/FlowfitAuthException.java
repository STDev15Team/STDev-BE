package stdev.global.infra.exception.auth;

import stdev.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class FlowfitAuthException extends FlowfitException {
    public FlowfitAuthException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}