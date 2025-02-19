package springbook.tr.patient.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springbook.tr.patient.model.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
