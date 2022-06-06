package teamfresh.demo.voc.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static teamfresh.demo.compensation.domain.CompensationStatus.REQUESTED;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teamfresh.demo.common.domain.Responsibility;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.repository.CompensationRepository;
import teamfresh.demo.compensation.dto.CompensationResponse;
import teamfresh.demo.compensation.exception.NotExistCompensationException;
import teamfresh.demo.penalty.domain.Penalty;
import teamfresh.demo.voc.domain.Imputation;
import teamfresh.demo.voc.domain.VOC;
import teamfresh.demo.voc.domain.repository.VocRepository;
import teamfresh.demo.voc.dto.VocRequest;
import teamfresh.demo.voc.dto.VocResponse;
import teamfresh.demo.voc.exception.NotExistVocException;

@ExtendWith(MockitoExtension.class)
class VocServiceTest {

    @Mock
    private VocRepository vocRepository;

    @Mock
    private CompensationRepository compensationRepository;

    @InjectMocks
    private VocService vocService;

    private Compensation compensation;
    private VocRequest request;
    private VOC voc;
    private Penalty penalty;

    @BeforeEach
    void setUp() {
        penalty = Penalty.builder()
            .compensation(compensation)
            .cost(10000)
            .content("패널티 내용")
            .build();

        compensation = Compensation.builder()
            .id(10L)
            .status(REQUESTED)
            .content("배상 청구 내용")
            .title("배상 청구 제목")
            .build();

        request = new VocRequest(
            "홍",
            "홍",
            "01044443333",
            1,
            "귀책 내용",
            true,
            false,
            null,
            10L
        );

        voc = VOC.builder()
            .id(10L)
            .compensation(compensation)
            .imputationContents("귀책 내용")
            .driverConfirmationStatus(true)
            .objectionStatus(false)
            .responsibility(new Responsibility("홍", "홍", "01044443333"))
            .imputation(Imputation.DELIVERY)
            .build();
    }

    @Test
    @DisplayName("voc 등록 성공")
    void createVoc_success() {
        // given
        given(compensationRepository.findById(anyLong())).willReturn(Optional.ofNullable(compensation));

        // when
        when(vocRepository.save(any(VOC.class))).thenReturn(voc);

        vocService.createVoc(request);

        // then
        verify(vocRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("voc 등록 실패, 배상 청구가 존재하지 않음")
    void createVoc_fails() {
        // given
        given(compensationRepository.findById(anyLong())).willThrow(
            NotExistCompensationException.class);

        // when, then
        assertThrows(NotExistCompensationException.class, () -> vocService.createVoc(request));
    }


    @Test
    @DisplayName("voc 상세 조회 실패, 해당 voc가 존재하지 않음")
    void getVoc_fails() {
        // given
        given(vocRepository.getVoc(anyLong())).willThrow(NotExistVocException.class);

        // when, then
        assertThrows(NotExistCompensationException.class, () -> vocService.getVoc(100L));
    }

    @Test
    @DisplayName("voc 상세 조회 성공")
    void getVoc_success() {
        // given
        VocResponse response = new VocResponse(
            voc.getId(),
            voc.getResponsibility().getName(),
            voc.getImputationContents(),
            penalty.getContent(),
            voc.getDriverConfirmationStatus(),
            voc.getObjectionStatus(),
            CompensationResponse.from(voc.getCompensation())
        );
        given(vocRepository.getVoc(anyLong())).willReturn(Optional.of(response));

        // when
        VocResponse vocResponse = vocService.getVoc(10L);

        // then
        assertThat(vocResponse).isEqualTo(response);
    }

}