package teamfresh.demo.compensation.application;

import static teamfresh.demo.compensation.domain.CompensationStatus.REQUESTED;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.repository.CompensationRepository;
import teamfresh.demo.compensation.dto.CompensationRequest;
import teamfresh.demo.compensation.dto.CompensationResponse;
import teamfresh.demo.compensation.dto.CompensationsResponse;
import teamfresh.demo.compensation.exception.NotExistCompensationException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompensationService {

    private final CompensationRepository compensationRepository;

    @Transactional
    public Long createCompensation(CompensationRequest request) {
        Compensation savedCompensation = compensationRepository.save(
            request.toCompensation(REQUESTED)
        );
        return savedCompensation.getId();
    }

    public CompensationResponse getCompensation(Long compensationId) {
        return compensationRepository.getCompensation(compensationId).orElseThrow(
            NotExistCompensationException::new);
    }

    public List<CompensationsResponse> getCompensations() {
        return CompensationsResponse.from(compensationRepository.findAll());
    }
}
