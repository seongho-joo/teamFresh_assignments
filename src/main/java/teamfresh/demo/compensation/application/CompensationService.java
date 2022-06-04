package teamfresh.demo.compensation.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.CompensationRepository;
import teamfresh.demo.compensation.dto.CompensationsResponse;
import teamfresh.demo.compensation.dto.CompensationRequest;
import teamfresh.demo.compensation.dto.CompensationResponse;
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
            request.toCompensation(request.getTitle(), request.getContent())
        );
        return savedCompensation.getId();
    }

    public CompensationResponse getCompensationById(Long compensationId) {
        Compensation compensation = compensationRepository.findById(compensationId)
            .orElseThrow(NotExistCompensationException::new);

        return CompensationResponse.from(compensation);
    }

    public List<CompensationsResponse> getCompensationList() {
        return CompensationsResponse.from(compensationRepository.findAll());
    }
}
