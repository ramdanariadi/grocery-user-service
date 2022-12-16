package id.grocery.tunas.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public ApiRequestException(String message, HttpStatus httpStatus){
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ApiRequestException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}