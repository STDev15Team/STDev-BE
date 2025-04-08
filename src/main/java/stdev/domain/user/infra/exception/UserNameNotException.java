package stdev.domain.user.infra.exception;

import stdev.global.infra.exception.FlowfitException;
import org.springframework.http.HttpStatus;

public class UserNameNotException extends FlowfitException {
    public UserNameNotException() {
        super(HttpStatus.UNAUTHORIZED, "아이디가 일치하지 않습니다.");
    }
}
