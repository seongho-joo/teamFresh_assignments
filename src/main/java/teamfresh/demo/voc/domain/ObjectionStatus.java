package teamfresh.demo.voc.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ObjectionStatus {
    NONE("NONE"),
    EXISTED("이의제기 존재");

    private final String description;
}
