package oasis.piloti.errors.exception;


import lombok.Getter;
import oasis.piloti.API.ApiBuilder;

import java.util.Map;

@Getter
public class Exception400 extends RuntimeException {
    Map<String, String> errors;

    public Exception400(Map<String, String> errors, String message) {
        super(message);
        this.errors = (errors != null) ? errors : null;
    }

    public ApiBuilder.ApiResponse<?> body(){
        return ApiBuilder.fail(errors, getMessage());
    }
}
