package springbook.tr.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import springbook.tr.exception.CustomException;
import springbook.tr.exception.ErrorCode;
import springbook.tr.patient.find.model.dto.IndividualPatientResponseDto;
import springbook.tr.patient.find.model.dto.PatientListResponseDto;
import springbook.tr.patient.model.entity.Patient;
import springbook.tr.user.model.entity.User;
import springbook.tr.user.model.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public IndividualPatientResponseDto findPatientByUserId(Long userId, Long patientId) {
		User user = findUserById(userId);

		Patient patient = user.getPatients().stream()
			.filter(p -> p.getId().equals(patientId))
			.findFirst()
			.orElseThrow(() -> new CustomException(ErrorCode.PATIENT_NOT_FOUND));

		return createIndividualPatientResponse(patient);
	}

	@Transactional(readOnly = true)
	public List<PatientListResponseDto> findAllPatientsByUserId(Long userId) {
		User user = findUserById(userId);

		return user.getPatients().stream()
			.map(PatientListResponseDto::fromEntity)
			.collect(Collectors.toList());
	}

	private User findUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
	}

	private IndividualPatientResponseDto createIndividualPatientResponse(Patient patient) {
		return IndividualPatientResponseDto.builder()
			.name(patient.getName())
			.age(patient.getAge())
			.birthDate(patient.getBirthdate())
			.gender(patient.getGender())
			.weight(patient.getWeight())
			.height(patient.getHeight())
			.build();
	}

}
