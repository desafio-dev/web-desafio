package br.com.bycoders.desafiodev.bankingservice.exceptions;

import br.com.bycoders.desafiodev.bankingservice.domains.records.ErrorResponse;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ExceptionApplicationAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorHandlerBadRequest(IllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorHandler = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
        return errorHandler;
    }

    @ExceptionHandler(value = {FileSizeLimitExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorHandlerBadRequest(FileSizeLimitExceededException ex, WebRequest request) {
        ErrorResponse errorHandler = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
        return errorHandler;
    }

    @ExceptionHandler(value = {MultipartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorHandlerBadRequest(MultipartException ex, WebRequest request) {
        ErrorResponse errorHandler = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
        return errorHandler;
    }

    @ExceptionHandler(value = {StringIndexOutOfBoundsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorHandlerBadRequest(StringIndexOutOfBoundsException ex, WebRequest request) {
        ErrorResponse errorHandler = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
        return errorHandler;
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorHandlerBadRequest(NullPointerException ex, WebRequest request) {
        ErrorResponse errorHandler = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
        return errorHandler;
    }

    @ExceptionHandler(value = {DateTimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse errorHandlerBadRequest(DateTimeException ex, WebRequest request) {
        ErrorResponse errorHandler = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), ZonedDateTime.now());
        return errorHandler;
    }


}
