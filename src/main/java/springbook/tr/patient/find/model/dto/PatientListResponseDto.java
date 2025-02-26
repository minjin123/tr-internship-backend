package springbook.tr.patient.find.model.dto;

import lombok.Builder;
import lombok.Getter;
import springbook.tr.patient.model.entity.Patient;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class PatientListResponseDto {

	private final Long id;
	private final String name;
	private final int age;
	private final LocalDate birthdate;
	private final String gender;
	private final BigDecimal height;
	private final BigDecimal weight;

	public static PatientListResponseDto fromEntity(Patient patient) {
		return PatientListResponseDto.builder()
			.id(patient.getId())
			.name(patient.getName())
			.age(patient.getAge())
			.birthdate(patient.getBirthdate())
			.gender(patient.getGender())
			.height(patient.getHeight())
			.weight(patient.getWeight())
			.build();
	}

}