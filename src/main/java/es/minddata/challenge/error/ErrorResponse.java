package es.minddata.challenge.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String stackTrace;

    private final String message;

    private final String timestamp;
}