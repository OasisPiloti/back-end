package oasis.piloti.errors.exception;


import lombok.Getter;
import oasis.piloti.API.ApiBuilder;

@Getter
public class Exception403 extends RuntimeException {
    public Exception403(String message) {
        super(message);
    }

    public ApiBuilder.ApiResponse<?> body(){
        return ApiBuilder.error(getMessage());
    }
}
