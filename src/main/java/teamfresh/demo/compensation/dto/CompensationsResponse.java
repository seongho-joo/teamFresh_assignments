package teamfresh.demo.compensation.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import teamfresh.demo.compensation.domain.Compensation;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompensationsResponse {

    private Long id;
    private String title;

    public static CompensationsResponse from(Compensation compensation) {
        return new CompensationsResponse(compensation.getId(), compensation.getTitle());
    }

    public static List<CompensationsResponse> from(List<Compensation> compensations) {
        return compensations.stream()
            .map(CompensationsResponse::from)
            .collect(Collectors.toList());
    }
}
