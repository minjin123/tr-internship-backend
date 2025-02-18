package springbook.tr.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import springbook.tr.patient.find.model.dto.IndividualPatientResponseDto;
import springbook.tr.patient.find.model.dto.PatientListResponseDto;
import springbook.tr.patient.model.entity.Patient;
import springbook.tr.patient.model.repository.PatientRepository;
import springbook.tr.user.model.entity.User;
import springbook.tr.user.model.repository.UserRepository;

@Service
public class UserService {
	private final PatientRepository patientRepository;
	private final UserRepository userRepository;

	public UserService(PatientRepository patientRepository, UserRepository userRepository) {
		this.patientRepository = patientRepository;
		this.userRepository = userRepository;
	}

	public IndividualPatientResponseDto findPatientById(Long userId, Long patientId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
		Patient patient = user.getPatients().stream()
			.filter(p -> p.getId().equals(patientId))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Patient not found"));
		return IndividualPatientResponseDto.builder()
			.name(patient.getName())
			.age(patient.getAge())
			.birthDate(patient.getBirthdate())
			.gender(patient.getGender())
			.weight(patient.getWeight())
			.height(patient.getHeight())
			.build();
	}

	public List<PatientListResponseDto> findAllPatientsByUserId(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("User not found"));

		return user.getPatients().stream()
			.map(PatientListResponseDto::new)
			.collect(Collectors.toList());
	}

}
