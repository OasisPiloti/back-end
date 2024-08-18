package oasis.piloti.api;

import oasis.piloti.enumerate.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    private static final Status STATUS_SUCCESS = Status.SUCCESS;

    private static final Status STATUS_FAILURE = Status.FAILURE;

    private static final Status STATUS_ERROR = Status.ERROR;

    private static <T> ResponseEntity<ApiResponse<T>> buildResponseWithData(Status status, T data, String message, HttpStatus httpStatus) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(status.getValue())
                .data(data)
                .message(message)
                .build();
        return new ResponseEntity<>(response, httpStatus);
    }

    private static <T> ResponseEntity<ApiResponse<T>> buildResponseWithoutData(Status status, String message, HttpStatus httpStatus) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(status.getValue())
                .data(null)
                .message(message)
                .build();
        return new ResponseEntity<>(response, httpStatus);
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildSuccessResponseWithData(T data, String message, HttpStatus httpStatus) {
        return buildResponseWithData(STATUS_SUCCESS, data, message, httpStatus);
    }

    public static ResponseEntity<ApiResponse<Void>> buildSuccessResponseWithoutData(String message, HttpStatus httpStatus) {
        return buildResponseWithoutData(STATUS_SUCCESS, message, httpStatus);
    }

    public static ResponseEntity<ApiResponse<Void>> buildFailureResponse(String message, HttpStatus httpStatus) {
        return buildResponseWithoutData(STATUS_FAILURE, message, httpStatus);
    }

    public static ResponseEntity<ApiResponse<Void>> buildErrorResponse(String message, HttpStatus httpStatus) {
        return buildResponseWithoutData(STATUS_ERROR, message, httpStatus);
    }
}
