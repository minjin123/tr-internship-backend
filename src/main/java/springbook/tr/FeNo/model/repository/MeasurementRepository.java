package springbook.tr.FeNo.model.repository;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springbook.tr.FeNo.model.entity.Measurement;
import springbook.tr.patient.model.entity.Patient;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

	@Query("SELECT m FROM Measurement m WHERE m.patient = :patient AND FUNCTION('DATE', m.createdAt) = :date ORDER BY m.createdAt ASC")
	Optional<List<Measurement>> findByPatientAndCreatedAt(Patient patient, @Param("date") LocalDate date);
}
