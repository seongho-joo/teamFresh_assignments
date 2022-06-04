package teamfresh.demo.compensation.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static teamfresh.demo.compensation.domain.CompensationStatus.REQUESTED;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.CompensationRepository;
import teamfresh.demo.compensation.dto.CompensationsResponse;
import teamfresh.demo.compensation.dto.CompensationRequest;
import teamfresh.demo.compensation.dto.CompensationResponse;
import teamfresh.demo.compensation.exception.NotExistCompensationException;

@ExtendWith(MockitoExtension.class)
class CompensationServiceTest {

    @Mock
    private CompensationRepository compensationRepository;

    @InjectMocks
    private CompensationService compensationService;

    private Compensation compensation;
    private CompensationRequest request;

    @BeforeEach
    void setUp() {
        request = new CompensationRequest("배상 청구 제목", "배상 청구 내용");

        compensation = Compensation.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .status(REQUESTED)
            .build();
    }

    @Nested
    @DisplayName("배상 청구 등록 테스트")
    class CreateCompensationTest {

        @Test
        @DisplayName("배상 청구 등록 성공")
        void createCompensation_success() {
            // when
            when(compensationRepository.save(any(Compensation.class))).thenReturn(compensation);

            Long compensationId = compensationService.createCompensation(request);

            // then
            assertThat(compensationId).isEqualTo(compensation.getId());
        }
    }

    @Nested
    @DisplayName("배상 청구 조회 테스트")
    class GetCompensationTest {

        @Test
        @DisplayName("특정 배상 청구 조회 성공")
        void getCompensationById_success() {
            // given
            given(compensationRepository.findById(anyLong())).willReturn(Optional.ofNullable(compensation));

            // when
            CompensationResponse foundCompensation = compensationService.getCompensationById(10L);

            // then
            assertThat(foundCompensation.getId()).isEqualTo(compensation.getId());
        }

        @Test
        @DisplayName("특정 배상 청구 조회 실패, 존재 하지 않는 배상 청구는 조회하지 못함")
        void getCompensationById_fail() {
            // when, then
            assertThrows(NotExistCompensationException.class,
                () -> compensationService.getCompensationById(1000L));
        }

        @Test
        @DisplayName("배상 청구 목록 조회 성공")
        void getCompensationList_success() {
            // given
            List<Compensation> compensations = List.of(
                createCompensation(10L),
                createCompensation(11L),
                createCompensation(12L),
                createCompensation(13L),
                createCompensation(14L)
            );
            given(compensationRepository.findAll()).willReturn(compensations);

            // when
            List<CompensationsResponse> list = compensationService.getCompensationList();

            // then
            assertThat(list).hasSize(5);
        }

        private Compensation createCompensation(long id) {
            return Compensation.builder()
                .id(id)
                .title("제목")
                .status(REQUESTED)
                .content("내용")
                .build();
        }

    }
}