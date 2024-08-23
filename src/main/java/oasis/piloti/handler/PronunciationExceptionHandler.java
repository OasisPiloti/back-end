package oasis.piloti.handler;

import oasis.piloti.api.ApiResponse;
import oasis.piloti.exception.PronunciationAccuracyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static oasis.piloti.api.ResponseBuilder.buildFailureResponse;

@RestControllerAdvice
public class PronunciationExceptionHandler {

    @ExceptionHandler(PronunciationAccuracyException.class)
    public ResponseEntity<ApiResponse<Void>> handlePronunciationAccuracyException(PronunciationAccuracyException e) {
        return buildFailureResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
