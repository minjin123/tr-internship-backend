package springbook.tr.patient.find.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class IndividualPatientResponseDto {
	private final String name;
	private final Integer age;
	private final LocalDate birthDate;

	@Size(min = 1, max = 1)
	private final String gender;

	private final BigDecimal weight;
	private final BigDecimal height;

}
