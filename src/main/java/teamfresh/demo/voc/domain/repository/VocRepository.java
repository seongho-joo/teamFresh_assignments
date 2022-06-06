package teamfresh.demo.voc.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamfresh.demo.voc.domain.VOC;

public interface VocRepository extends JpaRepository<VOC, Long>, VocCustomRepository {

}
