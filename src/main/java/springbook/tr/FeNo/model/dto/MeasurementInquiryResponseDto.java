package springbook.tr.FeNo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementInquiryResponseDto {
	private LocalDateTime localDateTime;
	private BigDecimal flowRate;
	private BigDecimal nitricOxide;
	private BigDecimal pressure;
}
