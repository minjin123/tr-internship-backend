package springbook.tr.FeNo.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class PressureCalculate {

	public BigDecimal calculate(BigDecimal value) {
		BigDecimal cmH2O = new BigDecimal("0.0101972");

		return value.multiply(cmH2O).setScale(2, RoundingMode.HALF_UP);
	}
}