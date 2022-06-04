package teamfresh.demo.penalty.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PenaltyResponseMessage {
    CREATE_PENALTY("패널티 등록 성공");

    private final String message;
}
