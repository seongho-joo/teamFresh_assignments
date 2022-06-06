package teamfresh.demo.compensation.domain.repository;

import java.util.Optional;
import teamfresh.demo.compensation.dto.CompensationResponse;

public interface CompensationCustomRepository {

    Optional<CompensationResponse> getCompensation(Long compensationId);

}
