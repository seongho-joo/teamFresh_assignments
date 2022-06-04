package teamfresh.demo.compensation.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import teamfresh.demo.compensation.domain.Compensation;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompensationResponse {

    private Long id;
    private String title;
    private String content;
    private String status;

    public static CompensationResponse from(Compensation compensation) {
        return new CompensationResponse(
            compensation.getId(),
            compensation.getTitle(),
            compensation.getContent(),
            compensation.getStatus().getDescription()
        );
    }
}
