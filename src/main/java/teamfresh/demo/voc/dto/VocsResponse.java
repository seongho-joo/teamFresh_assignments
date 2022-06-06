package teamfresh.demo.voc.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import teamfresh.demo.voc.domain.VOC;

@Getter
@AllArgsConstructor
public class VocsResponse {

    private Long id;

    private String responsibleName;

    public static VocsResponse from(VOC voc) {
        return new VocsResponse(voc.getId(), voc.getResponsibility().getName());
    }

    public static List<VocsResponse> from(List<VOC> vocList) {
        return vocList.stream()
            .map(VocsResponse::from)
            .collect(Collectors.toList());
    }
}
