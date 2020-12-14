package com.aeradron.enigma.service.exceptions;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ErrorResponse {

    @ApiModelProperty(required = true, value = "Time Stamp")
    private Date timeStamp;
    @ApiModelProperty(required = true, value = "Status")
    private Integer status;
    @ApiModelProperty(required = true, value = "Error")
    private String error;
    @ApiModelProperty(required = true, value = "Exception")
    private String exception;
    @ApiModelProperty(required = true, value = "Message")
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(Integer status, String error, String exception, String message) {
        this.status = status;
        this.error = error;
        this.exception = exception;
        this.message = message;
        this.timeStamp = new Date();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
