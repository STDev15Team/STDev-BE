package stdev.domain.user.infra.exception;


import stdev.global.infra.exception.auth.FlowfitAuthException;
import org.springframework.http.HttpStatus;

public class InvalidRoleException extends FlowfitAuthException {
    public InvalidRoleException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
