package teamfresh.demo.voc.domain.repository;

import java.util.Optional;
import teamfresh.demo.voc.dto.VocResponse;

public interface VocCustomRepository {

    Optional<VocResponse> getVoc(Long id);
}
