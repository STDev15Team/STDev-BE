package stdev.global.infra.exception;

import org.springframework.http.HttpStatus;

public class FlowfitException extends RuntimeException {

    private final HttpStatus status;

    public FlowfitException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
