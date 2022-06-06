package teamfresh.demo.voc.domain.repository;

import static teamfresh.demo.penalty.domain.QPenalty.penalty;
import static teamfresh.demo.voc.domain.QVOC.vOC;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import teamfresh.demo.compensation.dto.QCompensationResponse;
import teamfresh.demo.voc.dto.QVocResponse;
import teamfresh.demo.voc.dto.VocResponse;

@RequiredArgsConstructor
public class VocCustomRepositoryImpl implements VocCustomRepository {

    private final JPAQueryFactory factory;

    @Override
    public Optional<VocResponse> getVoc(Long id) {
        return Optional.ofNullable(factory.select(new QVocResponse(
                vOC.id,
                vOC.responsibility.name,
                vOC.imputationContents,
                penalty.content,
                vOC.driverConfirmationStatus,
                vOC.objectionStatus,
                new QCompensationResponse(
                    vOC.compensation.id,
                    vOC.compensation.title,
                    vOC.compensation.content,
                    vOC.compensation.status,
                    vOC.compensation.createdAt
                )
            ))
            .from(vOC)
            .join(penalty)
            .on(penalty.compensation.id.eq(vOC.compensation.id))
            .where(vOC.id.eq(id))
            .fetchOne());
    }
}
