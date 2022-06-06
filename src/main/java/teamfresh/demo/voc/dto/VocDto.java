package teamfresh.demo.voc.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.voc.domain.VOC;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VocDto {

    private Long id;
    private String responsibleName;
    private String imputationContents;
    private Boolean driverConfirmed;
    private Boolean objectionStatus;

    @QueryProjection
    public VocDto(Long id, String responsibleName, String imputationContents, Boolean driverConfirmed, Boolean objectionStatus) {
        this.id = id;
        this.responsibleName = responsibleName;
        this.imputationContents = imputationContents;
        this.driverConfirmed = driverConfirmed;
        this.objectionStatus = objectionStatus;
    }

    public static VocDto from(VOC voc) {
        return new VocDto(
            voc.getId(),
            voc.getResponsibility().getName(),
            voc.getImputationContents(),
            voc.getDriverConfirmationStatus(),
            voc.getObjectionStatus()
        );
    }
}
