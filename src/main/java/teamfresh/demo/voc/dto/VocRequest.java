package teamfresh.demo.voc.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.common.domain.Responsibility;
import teamfresh.demo.compensation.domain.Compensation;
import teamfresh.demo.voc.domain.Imputation;
import teamfresh.demo.voc.domain.VOC;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VocRequest {

    @NotBlank(message = "담당자 회사를 입력해주세요.")
    private String responsibleCompanyName;

    @NotBlank(message = "담당자 이름을 입력해주세요.")
    private String responsibleName;

    @NotBlank(message = "담당자 연락처를 입력해주세요.")
    private String responsiblePhoneNumber;

    @Max(value = 2, message = "1과 2만 입력할 수 있습니다.")
    @Min(value = 1, message = "1과 2만 입력할 수 있습니다.")
    private Integer imputationCodes;

    @NotBlank(message = "귀책 내용을 입력해주세요.")
    private String imputationContents;

    private Boolean objectionStatus;

    private String objectionContent;

    @NotNull(message = "배상 청구를 선택해주세요.")
    private Long compensationId;

    public VOC toVoc(Compensation compensation) {
        return VOC.builder()
            .responsibility(new Responsibility(
                responsibleCompanyName,
                responsibleName,
                responsiblePhoneNumber))
            .imputation(Imputation.from(imputationCodes))
            .compensation(compensation)
            .imputationContents(imputationContents)
            .driverConfirmationStatus(false)
            .objectionStatus(objectionStatus)
            .objectionContent(objectionContent)
            .build();
    }
}
