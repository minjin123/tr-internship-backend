package springbook.tr.patient.update.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class InfoUpdateResponseDto {
	private final String name;
	private final int age;
	private final LocalDate birthDate;

	@Size(min = 1, max = 1)
	private final String gender;

	private final BigDecimal weight;
	private final BigDecimal height;

}
