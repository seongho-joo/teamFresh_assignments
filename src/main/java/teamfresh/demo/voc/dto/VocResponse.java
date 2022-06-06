package teamfresh.demo.voc.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.compensation.dto.CompensationResponse;
import teamfresh.demo.penalty.domain.Penalty;
import teamfresh.demo.voc.domain.VOC;

@Getter
@NoArgsConstructor
public class VocResponse {

    Long id;

    String responsibleName;

    String imputationContents;

    String penaltyContents;

    Boolean driverConfirmed;

    Boolean objectionStatus;

    CompensationResponse compensation;

    @QueryProjection
    public VocResponse(Long id, String responsibleName, String imputationContents,
        String penaltyContents, Boolean driverConfirmed, Boolean objectionStatus,
        CompensationResponse compensation) {
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
            CompensationResponse.from(voc.getCompensation())
            );
    }
}
