package teamfresh.demo.compensation.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.CompensationStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompensationDto {

    private Long id;
    private String title;
    private String content;
    private CompensationStatus status;
    private LocalDateTime createdAt;


    @QueryProjection
    public CompensationDto(Long id, String title, String content,
        CompensationStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static CompensationDto from(Compensation compensation) {
        return new CompensationDto(
            compensation.getId(),
            compensation.getTitle(),
            compensation.getContent(),
            compensation.getStatus(),
            compensation.getCreatedAt()
        );
    }


}
