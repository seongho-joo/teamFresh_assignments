package teamfresh.demo.penalty.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.repository.CompensationRepository;
import teamfresh.demo.compensation.exception.NotExistCompensationException;
import teamfresh.demo.penalty.domain.PenaltyRepository;
import teamfresh.demo.penalty.dto.PenaltyRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;
    private final CompensationRepository compensationRepository;

    @Transactional
    public void createPenalty(PenaltyRequest request) {
        Compensation compensation = compensationRepository.findById(request.getCompensationId())
            .orElseThrow(
                NotExistCompensationException::new);

        penaltyRepository.save(request.toPenalty(compensation));
    }
}
