package teamfresh.demo.voc.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Imputation {
    DELIVERY("배송사"),
    CLIENT("고객사");

    private final String description;
}
