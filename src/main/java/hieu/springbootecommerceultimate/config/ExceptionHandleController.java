package hieu.springbootecommerceultimate.config;

import hieu.springbootecommerceultimate.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandleController {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        return ResponseEntity.ok(ApiResponse.builder()
                        .message(e.getMessage())
                        .data(e.getCause())
                .build());
    }
}
