package springbook.tr.patient.registration.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springbook.tr.patient.model.entity.Patient;
import springbook.tr.patient.model.repository.PatientRepository;
import springbook.tr.patient.registration.model.dto.RegistrationRequestDto;
import springbook.tr.patient.registration.model.dto.RegistrationResponseDto;
import springbook.tr.user.model.entity.User;
import springbook.tr.user.model.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PatientRegistrationService {

	private final PatientRepository patientRepository;
	private final UserRepository userRepository;

	public RegistrationResponseDto register(RegistrationRequestDto registrationRequestDto) {
		User user = findUerById(registrationRequestDto.getUserId());
		Patient patient = createPatientFromDto(registrationRequestDto, user);
		patientRepository.save(patient);

		return createResponse(patient);
	}

	private User findUerById(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

	private Patient createPatientFromDto(RegistrationRequestDto dto, User user) {
		return dto.toEntity(user);
	}

	private RegistrationResponseDto createResponse(Patient patient) {
		return new RegistrationResponseDto(patient.getId());
	}
}
