package net.yassdg.bilingservice.handler;

import net.yassdg.bilingservice.data.BillTestData;
import net.yassdg.bilingservice.exception.BillNotFoundException;
import net.yassdg.bilingservice.handler.response.ErrorResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.core.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();
    private ResponseEntity<ErrorResponseModel> response;

    @BeforeEach
    void init() {
        response = null;
    }

    @Test
    void shouldHandleBillNotFoundException() {
        final BillNotFoundException billNotFoundException = new BillNotFoundException(BillTestData.ERROR_MESSAGE);

        response = handler.handleNotFound(billNotFoundException);

        verifyErrorResponse(response, HttpStatus.NOT_FOUND, billNotFoundException.getMessage());
    }

    @Test
    void shouldHandlePropertyReferenceException() {
       final PropertyReferenceException propertyReferenceException = Mockito.mock( PropertyReferenceException.class);
       Mockito.when(propertyReferenceException.getMessage()).thenReturn(BillTestData.ERROR_MESSAGE);

       response = handler.handleInvalidProperty(propertyReferenceException);

        verifyErrorResponse(response, HttpStatus.BAD_REQUEST, propertyReferenceException.getMessage());
    }

    @Test
    void shouldHandleGenericException() {
        final Exception runtimeException = new RuntimeException(BillTestData.ERROR_MESSAGE);

        response = handler.handleOthers(runtimeException);

        verifyErrorResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, runtimeException.getMessage());
    }


    private  void verifyErrorResponse(
            final ResponseEntity<ErrorResponseModel> response,
            final HttpStatus status,
            final String message
    ) {
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(status);
        final ErrorResponseModel errorResponseModel = response.getBody();
        assertThat(errorResponseModel)
                .isNotNull()
                .hasFieldOrPropertyWithValue("status", status.value())
                .hasFieldOrPropertyWithValue("error", status.getReasonPhrase())
                .hasFieldOrPropertyWithValue("message", message);
        assertThat(response.getBody().date())
                .isNotNull()
                .isCloseTo(LocalDateTime.now(), within(Duration.ofSeconds(2)));
    }

}