package teamfresh.demo.compensation.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static teamfresh.demo.compensation.domain.CompensationStatus.REQUESTED;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import teamfresh.demo.BaseRepositoryTest;
import teamfresh.demo.common.domain.Responsibility;
import teamfresh.demo.compensation.domain.repository.CompensationRepository;
import teamfresh.demo.compensation.dto.CompensationResponse;
import teamfresh.demo.compensation.exception.NotExistCompensationException;
import teamfresh.demo.penalty.domain.Penalty;
import teamfresh.demo.penalty.domain.PenaltyRepository;
import teamfresh.demo.voc.domain.Imputation;
import teamfresh.demo.voc.domain.VOC;
import teamfresh.demo.voc.domain.repository.VocRepository;

@BaseRepositoryTest
class CompensationRepositoryTest {

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private VocRepository vocRepository;

    @Autowired
    private PenaltyRepository penaltyRepository;

    private Compensation compensation;

    @BeforeEach
    void setUp() {
        compensation = Compensation.builder()
            .title("제목")
            .content("배상 청구 내용")
            .status(REQUESTED)
            .build();
    }

    @Test
    @DisplayName("배상 청구 등록 테스트")
    void createCompensation() {
        // when
        Compensation savedCompensation = compensationRepository.save(compensation);

        // then
        assertThat(savedCompensation).isNotNull();
    }

    @Test
    @DisplayName("배상 상태 수정")
    void updateCompensation() {
        // given
        Compensation savedCompensation = compensationRepository.save(compensation);

        // when
        savedCompensation.updateStatus(CompensationStatus.ONGOING);

        // then
        assertThat(savedCompensation.getStatus()).isEqualTo(CompensationStatus.ONGOING);
    }

    @Test
    @DisplayName("배상 상세보기 테스트")
    void getCompensation() {
        // given
        Compensation savedCompensation = compensationRepository.save(compensation);
        VOC voc = VOC.builder()
            .compensation(savedCompensation)
            .driverConfirmationStatus(true)
            .responsibility(new Responsibility("company", "name", "01012341234"))
            .objectionStatus(false)
            .imputation(Imputation.DELIVERY)
            .imputationContents("imputation contents")
            .build();
        Penalty penalty = Penalty.builder()
            .compensation(savedCompensation)
            .content("penalty contents")
            .cost(10000)
            .build();

        vocRepository.save(voc);
        penaltyRepository.save(penalty);

        // when
        CompensationResponse response = compensationRepository.getCompensation(
            savedCompensation.getId()).orElseThrow(
            NotExistCompensationException::new);

        // then
        assertThat(response.getPenaltyCost()).isEqualTo(penalty.getCost());
        assertThat(response.getVoc().getId()).isEqualTo(voc.getId());
    }

    @Test
    @DisplayName("배상 목록 조회 테스트")
    void findCompensationList() {
        // given
        List<Compensation> compensationList = List.of(
            createCompensations(),
            createCompensations(),
            createCompensations(),
            createCompensations(),
            createCompensations()
        );
        compensationRepository.saveAll(compensationList);

        // when
        List<Compensation> list = compensationRepository.findAll();

        // then
        assertThat(list).hasSize(5);
    }

    private Compensation createCompensations() {
        return Compensation.builder()
            .title("배상 제목")
            .content("배상 내용")
            .status(REQUESTED)
            .build();
    }
}