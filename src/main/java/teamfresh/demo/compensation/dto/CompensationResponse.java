package teamfresh.demo.compensation.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.compensation.domain.CompensationStatus;
import teamfresh.demo.voc.dto.VocDto;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompensationResponse {

    private Long id;
    private String title;
    private String content;
    private CompensationStatus status;
    private VocDto voc;
    private Integer penaltyCost;
    private LocalDateTime createdAt;

    @QueryProjection
    public CompensationResponse(Long id, String title, String content,
        CompensationStatus status, VocDto voc,Integer penaltyCost, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.voc = voc;
        this.penaltyCost = penaltyCost;
        this.createdAt = createdAt;
    }
}
