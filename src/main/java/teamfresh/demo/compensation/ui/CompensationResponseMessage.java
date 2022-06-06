package teamfresh.demo.compensation.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompensationResponseMessage {
    CREATE_COMPENSATION("배상 청구 등록 성공"),
    GET_COMPENSATION("배상 청구 상세보기 성공"),
    GET_COMPENSATIONS("배상 청구 목록 조회 성공");

    private final String message;
}
