package oasis.piloti.errors.exception;


import lombok.Getter;
import oasis.piloti.API.ApiBuilder;

@Getter
public class Exception404 extends RuntimeException {
    public Exception404(String message) {
        super(message);
    }

    public ApiBuilder.ApiResponse<?> body(){
        return ApiBuilder.error(getMessage());
    }
}