package teamfresh.demo.voc.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Imputation {
    DELIVERY(1, "배송사"),
    CLIENT(2, "고객사");

    private final Integer code;
    private final String name;

    @JsonCreator
    public static Imputation from(Integer code) {
        for (Imputation value : Imputation.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }
}
