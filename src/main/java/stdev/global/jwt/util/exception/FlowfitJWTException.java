package stdev.global.jwt.util.exception;

import stdev.global.infra.exception.auth.FlowfitAuthException;
import org.springframework.http.HttpStatus;

public class FlowfitJWTException extends FlowfitAuthException {
    public FlowfitJWTException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
