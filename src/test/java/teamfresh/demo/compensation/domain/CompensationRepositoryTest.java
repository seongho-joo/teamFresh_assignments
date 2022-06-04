package teamfresh.demo.compensation.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import teamfresh.demo.BaseRepositoryTest;

@BaseRepositoryTest
class CompensationRepositoryTest {

    @Autowired
    private CompensationRepository compensationRepository;

    @Test
    @DisplayName("배상 청구 등록 테스트")
    void createCompensation() {
        // given
        Compensation com = Compensation.builder()
            .title("제목")
            .content("배상 청구 내용")
            .status(CompensationStatus.REQUESTED)
            .build();

        // when
        Compensation savedCompensation = compensationRepository.save(com);

        // then
        assertThat(savedCompensation).isNotNull();
    }

    @Test
    @DisplayName("배상 상태 수정")
    void updateCompensation() {
        // given
        Compensation com = Compensation.builder()
            .title("제목")
            .content("배상 청구 내용")
            .status(CompensationStatus.REQUESTED)
            .build();

        Compensation compensation = compensationRepository.save(com);

        // when
        compensation.updateStatus(CompensationStatus.ONGOING);

        // then
        assertThat(compensation.getStatus()).isEqualTo(CompensationStatus.ONGOING);
    }
}