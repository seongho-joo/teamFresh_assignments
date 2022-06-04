package teamfresh.demo.compensation.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static teamfresh.demo.compensation.domain.CompensationStatus.REQUESTED;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import teamfresh.demo.BaseRepositoryTest;
import teamfresh.demo.compensation.exception.NotExistCompensationException;

@BaseRepositoryTest
class CompensationRepositoryTest {

    @Autowired
    private CompensationRepository compensationRepository;

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
    void findCompensationById() {
        // given
        Compensation savedCompensation = compensationRepository.save(compensation);

        // when
        Compensation foundCompensation = compensationRepository.findById(savedCompensation.getId())
            .orElseThrow(
                NotExistCompensationException::new);

        // then
        assertThat(foundCompensation).isEqualTo(savedCompensation);
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