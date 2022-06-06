package teamfresh.demo.voc.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.repository.CompensationRepository;
import teamfresh.demo.compensation.exception.NotExistCompensationException;
import teamfresh.demo.voc.domain.repository.VocRepository;
import teamfresh.demo.voc.dto.VocRequest;
import teamfresh.demo.voc.dto.VocResponse;
import teamfresh.demo.voc.dto.VocsResponse;
import teamfresh.demo.voc.exception.NotExistVocException;

@Slf4j
@Service
@RequiredArgsConstructor
public class VocService {

    private final VocRepository vocRepository;
    private final CompensationRepository compensationRepository;

    public void createVoc(VocRequest request) {
        Compensation compensation = compensationRepository.findById(request.getCompensationId())
            .orElseThrow(
                NotExistCompensationException::new);
        vocRepository.save(request.toVoc(compensation));
    }

    public VocResponse getVoc(Long id) {
        return vocRepository.getVoc(id).orElseThrow(NotExistVocException::new);
    }

    public List<VocsResponse> getVocLists() {
        return VocsResponse.from(vocRepository.findAll());
    }
}
