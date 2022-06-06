package teamfresh.demo.voc.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VocResponseMessage {
    CREATE_VOC("voc 등록 성공"),
    GET_VOC("voc 상세 조회 성공"),
    GET_VOC_LIST("voc 목록 조회 성공");

    private final String message;
}
