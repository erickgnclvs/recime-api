package br.erickgnclvs.recime_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseException extends RuntimeException {

    private final HttpStatus status;

    public ResponseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}