package stdev.domain.oauth2.infra.exception;

import stdev.global.infra.exception.auth.FlowfitAuthException;
import org.springframework.http.HttpStatus;

public class InvalidAccessTokenException extends FlowfitAuthException {
    public InvalidAccessTokenException() {
        super(HttpStatus.UNAUTHORIZED, "Access Token이 유효하지 않습니다.");
    }
}
