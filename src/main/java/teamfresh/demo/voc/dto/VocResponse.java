package teamfresh.demo.voc.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.compensation.dto.CompensationDto;
import teamfresh.demo.penalty.domain.Penalty;
import teamfresh.demo.voc.domain.VOC;

@Getter
@NoArgsConstructor
public class VocResponse {

    private Long id;
    private String responsibleName;
    private String imputationContents;
    private String penaltyContents;
    private Boolean driverConfirmed;
    private Boolean objectionStatus;
    private CompensationDto compensation;

    @QueryProjection
    public VocResponse(Long id, String responsibleName, String imputationContents,
        String penaltyContents, Boolean driverConfirmed, Boolean objectionStatus,
        CompensationDto compensation) {
        this.id = id;
        this.responsibleName = responsibleName;
        this.imputationContents = imputationContents;
        this.penaltyContents = penaltyContents;
        this.driverConfirmed = driverConfirmed;
        this.objectionStatus = objectionStatus;
        this.compensation = compensation;
    }


    public static VocResponse of(VOC voc, Penalty penalty){
        return new VocResponse(
            voc.getId(),
            voc.getResponsibility().getName(),
            voc.getImputationContents(),
            penalty.getContent(),
            voc.getDriverConfirmationStatus(),
            voc.getObjectionStatus(),
            CompensationDto.from(voc.getCompensation())
            );
    }
}
