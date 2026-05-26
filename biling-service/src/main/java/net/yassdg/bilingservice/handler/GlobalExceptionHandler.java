package net.yassdg.bilingservice.handler;

import lombok.extern.slf4j.Slf4j;
import net.yassdg.bilingservice.exception.BillNotFoundException;
import net.yassdg.bilingservice.handler.response.ErrorResponseModel;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BillNotFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleNotFound(BillNotFoundException exception) {
        log.error("bill not found exception with message = {}", exception.getMessage(), exception);
        return build(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErrorResponseModel> handleInvalidProperty(PropertyReferenceException exception) {
        log.error("property reference exception  with message = {}", exception.getMessage(), exception);
        return build(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handleOthers(Exception exception) {
        log.error("exception of type <{}> with message = {}", exception.getClass().getName(), exception.getMessage(), exception);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    private ResponseEntity<ErrorResponseModel> build(final HttpStatus status, final Exception exception) {
        final HttpStatus finalStatus = Optional.of(status).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity
                .status(finalStatus)
                .body(
                        ErrorResponseModel
                                .builder()
                                .date(LocalDateTime.now())
                                .status(finalStatus.value())
                                .error(finalStatus.getReasonPhrase())
                                .message(exception.getMessage())
                                .build()
                );

    }
}