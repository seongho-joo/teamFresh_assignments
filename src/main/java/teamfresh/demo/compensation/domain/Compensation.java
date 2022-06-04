package teamfresh.demo.compensation.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import teamfresh.demo.common.domain.BaseEntity;
import teamfresh.demo.penalty.domain.Penalty;
import teamfresh.demo.voc.domain.VOC;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Compensation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private CompensationStatus status;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "penalty_id")
    private Penalty penalty;

    @OneToOne(mappedBy = "compensation", fetch = LAZY)
    private VOC voc;

    public void updateStatus(CompensationStatus status) {
        this.status = status;
    }
}
