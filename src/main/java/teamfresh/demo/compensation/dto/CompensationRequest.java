package teamfresh.demo.compensation.dto;

import static lombok.AccessLevel.PROTECTED;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.compensation.domain.CompensationStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class CompensationRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "배상 내용을 입력해주세요.")
    private String content;

    public Compensation toCompensation(CompensationStatus status) {
        return Compensation.builder()
            .title(title)
            .content(content)
            .status(status)
            .build();
    }
}
