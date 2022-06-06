package teamfresh.demo.penalty.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.penalty.domain.Penalty;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PenaltyRequest {

    @NotBlank(message = "패널티 내용을 입력해주세요.")
    private String content;

    @NotNull(message = "배상 금액을 입력해주세요.")
    private Integer cost;

    @NotNull(message = "배상 청구를 선택해주세요.")
    private Long compensationId;

    public Penalty toPenalty(Compensation compensation) {
        return Penalty.builder()
            .compensation(compensation)
            .content(content)
            .cost(cost)
            .build();
    }
}
