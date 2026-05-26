package net.yassdg.bilingservice.handler.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonPropertyOrder({"status", "error", "message", "date",})
public record ErrorResponseModel(
        LocalDateTime date,
        int status,
        String error,
        String message
) {
}
