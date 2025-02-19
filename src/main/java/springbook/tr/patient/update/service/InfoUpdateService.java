package springbook.tr.patient.update.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import springbook.tr.patient.model.entity.Patient;
import springbook.tr.patient.model.repository.PatientRepository;
import springbook.tr.patient.update.model.dto.InfoUpdateRequestDto;
import springbook.tr.patient.update.model.dto.InfoUpdateResponseDto;

@Service
@RequiredArgsConstructor
public class InfoUpdateService {
	private final PatientRepository patientRepository;

	@Transactional
	public InfoUpdateResponseDto update(InfoUpdateRequestDto infoUpdateRequestDto) {
		Patient patient = findPatientById(infoUpdateRequestDto.getPatientId());
		patient.update(infoUpdateRequestDto.getWeight(), infoUpdateRequestDto.getHeight());

		return createResponse(patient);
	}

	private Patient findPatientById(Long userId) {
		return patientRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("Patient not found"));
	}

	private InfoUpdateResponseDto createResponse(Patient patient) {
		return InfoUpdateResponseDto.builder()
			.name(patient.getName())
			.age(patient.getAge())
			.birthDate(patient.getBirthdate())
			.gender(patient.getGender())
			.weight(patient.getWeight())
			.height(patient.getHeight())
			.build();
	}
}
