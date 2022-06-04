package teamfresh.demo.penalty.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import teamfresh.demo.BaseRepositoryTest;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.CompensationRepository;
import teamfresh.demo.compensation.domain.CompensationStatus;

@BaseRepositoryTest
class PenaltyRepositoryTest {

    @Autowired
    private PenaltyRepository penaltyRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    private Penalty penalty;
    private Compensation compensation;

    @BeforeEach
    void setUp() {
        compensation = Compensation.builder()
            .status(CompensationStatus.REQUESTED)
            .title("배상 청구 제목")
            .content("배상 청구 내용")
            .build();

        compensationRepository.save(compensation);

         penalty = Penalty.builder()
            .content("패널티 내용")
            .cost(10000)
            .compensation(compensation)
            .build();
    }

    @Test
    @DisplayName("패널티 등록 테스트")
    void createPenalty() {
        // when
        penaltyRepository.save(penalty);

        // then
        assertThat(penalty.getId()).isNotNull();
        assertThat(penalty.getCompensation()).isEqualTo(compensation);
    }
}