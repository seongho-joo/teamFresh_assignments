package teamfresh.demo.penalty.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.CompensationRepository;
import teamfresh.demo.compensation.domain.CompensationStatus;
import teamfresh.demo.penalty.domain.Penalty;
import teamfresh.demo.penalty.domain.PenaltyRepository;
import teamfresh.demo.penalty.dto.PenaltyRequest;

@ExtendWith(MockitoExtension.class)
class PenaltyServiceTest {

    @Mock
    private PenaltyRepository penaltyRepository;

    @Mock
    private CompensationRepository compensationRepository;

    @InjectMocks
    private PenaltyService penaltyService;

    private Compensation compensation;
    private Penalty penalty;
    private PenaltyRequest request;

    @BeforeEach
    void setUp() {
        compensation = new Compensation(10L, "배상 청구 제목", "배상 청구 내용", CompensationStatus.REQUESTED);
        request  = new PenaltyRequest("패널티 내용", 10000, 10L);
        penalty = new Penalty(10L, 10000, "패널티 내용", compensation);
    }

    @Test
    @DisplayName("패널티 등록 성공")
    void createPenalty_success() {
        // given
        given(compensationRepository.findById(anyLong())).willReturn(
            Optional.ofNullable(compensation));

        // when
        when(penaltyRepository.save(any(Penalty.class))).thenReturn(penalty);
        penaltyService.createPenalty(request);

        // then
        verify(penaltyRepository, times(1)).save(any());
    }
}