package teamfresh.demo.compensation.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamfresh.demo.compensation.domain.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Long>, CompensationCustomRepository {


}
