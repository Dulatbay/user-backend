package techpark.net.userbackend.exception.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import techpark.net.userbackend.dto.APIError;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<APIError> interruptedExceptionHandler(InterruptedException e) {
        log.error("Interrupted exception", e);
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = new APIError("Interrupted exception", "something interrupted process", getStackTrace(e));
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<APIError> executionExceptionHandler(ExecutionException e) {
        log.error("Execution exception", e);
        var statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = new APIError("Execution exception", "something executed incorrect", getStackTrace(e));
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<APIError> validationException(FeignException e) {
        log.error("Feign exception AUTH API ", e);
        HttpStatus statusCode;
        if (e.status() / 500 == 1) {
            statusCode = HttpStatus.valueOf(503);
        } else {
            statusCode = HttpStatus.valueOf(e.status());
        }

        log.error("Feign exception during API call", e);

        String errorMessage = e.contentUTF8();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(errorMessage);
            String message = jsonNode.has("message") ? jsonNode.get("message").asText() : errorMessage;
            return ResponseEntity.status(statusCode).body(new APIError("Feign exception", message, "[]"));
        } catch (Exception ex) {
            log.error("Failed to parse Feign exception response", ex);
            return ResponseEntity.status(503).body(new APIError("Feign exception", errorMessage, "[]"));
        }
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIError> argumentExceptionHandler(IllegalArgumentException e) {
        log.error("Argument exception: ", e);
        var errorResponse = new APIError("Illegal argument exception", e.getMessage(), getStackTrace(e));
        return ResponseEntity.status(400).body(errorResponse);
    }



    private static String getStackTrace(Throwable e) {
        filterStackTracesByProjectPackage(e);
        filterStackTracesByProjectPackage(e.getCause());
        return ExceptionUtils.getStackTrace(e).trim();
    }

    private static void filterStackTracesByProjectPackage(Throwable ex) {
        if (ex == null) return;

        StackTraceElement[] stackTraces = Arrays.stream(ex.getStackTrace())
                .filter(se -> se.getClassName().startsWith("com."))
                .toArray(StackTraceElement[]::new);

        ex.setStackTrace(stackTraces);
    }

}
