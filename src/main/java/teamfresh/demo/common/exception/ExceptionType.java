package teamfresh.demo.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import teamfresh.demo.compensation.exception.NotExistCompensationException;
import teamfresh.demo.voc.exception.NotExistVocException;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    NOT_EXIST_COMPENSATION("클레임이 존재하지 않습니다.", BAD_REQUEST, NotExistCompensationException.class),
    NOT_EXIST_VOC("voc가 존재하지 않습니다.", BAD_REQUEST, NotExistVocException.class);

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
