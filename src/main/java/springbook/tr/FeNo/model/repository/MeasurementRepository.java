package springbook.tr.FeNo.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springbook.tr.FeNo.model.entity.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

}
