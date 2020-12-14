package com.aeradron.enigma.controller.exception;

import com.aeradron.enigma.service.exceptions.InternalException;
import com.aeradron.enigma.service.exceptions.MandatoryFieldMissingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler({MandatoryFieldMissingException.class})
    public ResponseEntity<?> handleError(MandatoryFieldMissingException exception){
        logException(exception);
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getErrorResponse());
    }

    @ExceptionHandler({InternalException.class})
    public ResponseEntity<?> handleError(InternalException exception){
        logException(exception);
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getErrorResponse());
    }


    private void logException(Exception exception) {
        if (exception.getCause() != null) {
            logger.error(
                    "Exception in enigma: " + exception.getMessage()
                            + ", caused by, " + exception.getCause().getMessage(),
                    exception.getCause());
        } else {
            logger.error("Exception in enigma: " + exception.getMessage(), exception);
        }
    }
}
