package teamfresh.demo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

    private T data;
    private String message;

    public static <T> CommonResponse<T> of(T data, String message) {
        return new CommonResponse<>(data, message);
    }

    public static <T> CommonResponse<T> from(String message) {
        return new CommonResponse<>(null, message);
    }
}
