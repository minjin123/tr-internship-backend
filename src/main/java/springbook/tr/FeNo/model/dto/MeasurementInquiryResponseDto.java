package springbook.tr.FeNo.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbook.tr.FeNo.model.entity.Measurement;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementInquiryResponseDto {
	private LocalDateTime localDateTime;
	private double avgNitricOxide;
	private double avgPressure;

	public static MeasurementInquiryResponseDto from(Measurement measurement) {
		return MeasurementInquiryResponseDto.builder()
			.localDateTime(measurement.getCreatedAt())
			.avgNitricOxide(measurement.getNitricOxide())
			.avgPressure(measurement.getPressure())
			.build();
	}
}
