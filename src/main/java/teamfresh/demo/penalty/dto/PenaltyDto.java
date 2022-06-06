package teamfresh.demo.penalty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import teamfresh.demo.penalty.domain.Penalty;

@Getter
@AllArgsConstructor
public class PenaltyDto {

    private Long id;

    private Integer cost;

    private String content;

    public static PenaltyDto from(Penalty penalty) {
        return new PenaltyDto(penalty.getId(), penalty.getCost(), penalty.getContent());
    }
}
