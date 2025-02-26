package springbook.tr.patient.registration.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import springbook.tr.patient.model.entity.Patient;
import springbook.tr.user.model.entity.User;

@Getter
@Builder
@AllArgsConstructor
public class RegistrationRequestDto {
	private final Long userId;
	private final String name;
	private final int age;
	private final LocalDate birthDate;

	@Size(min = 1, max = 1)
	private final String gender;

	private final BigDecimal weight;
	private final BigDecimal height;

	public Patient toEntity(User user) {
		return new Patient(user, name, age, birthDate, gender, weight, height);
	}
}
