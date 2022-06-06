package teamfresh.demo.compensation.domain.repository;

import static teamfresh.demo.compensation.domain.QCompensation.compensation;
import static teamfresh.demo.penalty.domain.QPenalty.penalty;
import static teamfresh.demo.voc.domain.QVOC.vOC;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import teamfresh.demo.compensation.dto.CompensationResponse;
import teamfresh.demo.compensation.dto.QCompensationResponse;
import teamfresh.demo.voc.dto.QVocDto;

@RequiredArgsConstructor
public class CompensationCustomRepositoryImpl implements CompensationCustomRepository {

    private final JPAQueryFactory factory;

    @Override
    public Optional<CompensationResponse> getCompensation(Long compensationId) {
        return Optional.ofNullable(
            factory.select(
                    new QCompensationResponse(
                        compensation.id,
                        compensation.title,
                        compensation.content,
                        compensation.status,
                        new QVocDto(
                            vOC.id,
                            vOC.responsibility.name,
                            vOC.imputationContents,
                            vOC.driverConfirmationStatus,
                            vOC.objectionStatus
                        ),
                        penalty.cost,
                        compensation.createdAt)
                ).from(compensation)
                .join(penalty).on(penalty.compensation.id.eq(compensation.id))
                .join(vOC).on(vOC.compensation.id.eq(compensation.id))
                .where(compensation.id.eq(compensationId))
                .fetchOne()
        );
    }
}
