package teamfresh.demo.voc.domain;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamfresh.demo.common.domain.Responsibility;
import teamfresh.demo.compensation.domain.Compensation;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VOC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Responsibility responsibility;

    @Enumerated(STRING)
    private Imputation imputation;

    @Column(nullable = false)
    private Boolean driverConfirmationStatus;

    @Enumerated(STRING)
    private ObjectionStatus objectionStatus;

    private String objectionContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compensation_id")
    private Compensation compensation;
}
