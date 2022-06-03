package teamfresh.demo.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Map<String, String> errors;

    public static BaseErrorResponse of(String message, Map<String, String> errors) {
        return new BaseErrorResponse(
            message,
            LocalDateTime.now(),
            BAD_REQUEST.value(),
            BAD_REQUEST.getReasonPhrase(),
            errors
        );
    }

    public static BaseErrorResponse from(BaseException exception) {
        return new BaseErrorResponse(
            exception.getMessage(),
            exception.getTimestamp(),
            exception.getStatus().value(),
            exception.getStatus().getReasonPhrase(),
            null
        );
    }
}
