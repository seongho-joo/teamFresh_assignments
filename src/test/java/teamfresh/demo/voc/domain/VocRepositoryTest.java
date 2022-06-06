package teamfresh.demo.voc.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static teamfresh.demo.compensation.domain.CompensationStatus.REQUESTED;
import static teamfresh.demo.voc.domain.Imputation.DELIVERY;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import teamfresh.demo.BaseRepositoryTest;
import teamfresh.demo.common.domain.Responsibility;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.repository.CompensationRepository;
import teamfresh.demo.penalty.domain.Penalty;
import teamfresh.demo.penalty.domain.PenaltyRepository;
import teamfresh.demo.voc.domain.repository.VocRepository;
import teamfresh.demo.voc.dto.VocResponse;
import teamfresh.demo.voc.exception.NotExistVocException;

@BaseRepositoryTest
class VocRepositoryTest {

    @Autowired
    private VocRepository vocRepository;

    @Autowired
    private PenaltyRepository penaltyRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    private Compensation compensation;
    private VOC voc;
    private Penalty penalty;

    @BeforeEach
    void setUp() {
        compensation = Compensation.builder()
            .title("배상 청구 제목")
            .content("배상 청구 내용")
            .status(REQUESTED)
            .build();

        compensationRepository.save(compensation);

        penalty = Penalty.builder()
            .cost(1000)
            .content("패널티 내용")
            .compensation(compensation)
            .build();

        penaltyRepository.save(penalty);

        voc = VOC.builder()
            .responsibility(new Responsibility("team", "홍길동", "01011112222"))
            .imputation(DELIVERY)
            .imputationContents("귀책 내용")
            .driverConfirmationStatus(true)
            .objectionStatus(false)
            .compensation(compensation)
            .build();
    }

    @Test
    @DisplayName("voc 등록 테스트")
    void createVOC() {
        // when
        VOC savedVOC = vocRepository.save(voc);

        // then
        assertThat(savedVOC).isNotNull();
        assertThat(savedVOC.getCompensation()).isEqualTo(compensation);
    }

    @Test
    @DisplayName("voc 상세 조회 테스트")
    void getVOC() {
        // given
        VOC savedVOC = vocRepository.save(voc);

        // when
        VocResponse response = vocRepository.getVoc(savedVOC.getId()).orElseThrow(
            NotExistVocException::new);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getCompensation().getId()).isEqualTo(compensation.getId());
        assertThat(response.getPenaltyContents()).isEqualTo(penalty.getContent());
    }

    @Test
    @DisplayName("voc 목록 조회 테스트")
    void getVocLists() {
        // given
        List<VOC> vocs = List.of(
            createVoc(), createVoc(), createVoc(), createVoc()
        );
        vocRepository.saveAll(vocs);

        // when
        List<VOC> all = vocRepository.findAll();

        // then
        assertThat(all).hasSize(4);
    }

    private VOC createVoc() {
        Compensation build = Compensation.builder()
            .content("asdf")
            .title("asdf")
            .status(REQUESTED).build();

        compensationRepository.save(build);

        return VOC.builder()
            .responsibility(new Responsibility("홍", "홍", "0101234341"))
            .imputation(DELIVERY)
            .driverConfirmationStatus(false)
            .objectionStatus(false)
            .compensation(build)
            .build();
    }
}