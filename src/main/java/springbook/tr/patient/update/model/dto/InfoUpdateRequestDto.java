package springbook.tr.patient.update.model.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class InfoUpdateRequestDto {


	private final Long patientId;
	private final BigDecimal weight;
	private final BigDecimal height;

}
