package stdev.global.jwt.util.exception;


import stdev.global.infra.exception.auth.FlowfitAuthException;
import org.springframework.http.HttpStatus;

public class FlowfitJWTExpiredException extends FlowfitAuthException {
    public FlowfitJWTExpiredException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
