package com.aeradron.enigma.service.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class MandatoryFieldMissingException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorResponse errorResponse = new ErrorResponse();
    private Integer statusCode = HttpStatus.BAD_REQUEST.value();

    public MandatoryFieldMissingException(String message){
        super(message, new Throwable());
        errorResponse.setError("Bad Request");
        errorResponse.setException(RuntimeException.class.getName());
        errorResponse.setMessage(message);
        errorResponse.setStatus(statusCode);
        errorResponse.setTimeStamp(new Date());
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
}
