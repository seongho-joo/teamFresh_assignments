package teamfresh.demo.common.exception;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    ;

    private final String message;
    private final HttpStatus status;
    private final Class<? extends Exception> type;

    public static ExceptionType findException(Class<? extends BaseException> type) {
        return Arrays.stream(ExceptionType.values())
            .filter(exceptionType -> exceptionType.getType().equals(type))
            .findAny()
            .orElseThrow(UnexpectedException::new);
    }
}
