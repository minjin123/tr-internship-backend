package springbook.tr.patient.registration.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegistrationResponseDto {
	private final Long patientId;
}
