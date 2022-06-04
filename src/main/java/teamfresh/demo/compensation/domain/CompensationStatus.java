package teamfresh.demo.compensation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompensationStatus {
    REQUESTED("요청됨"),
    ONGOING("진행중"),
    COMPLETED("완료됨");

    private final String description;
}
