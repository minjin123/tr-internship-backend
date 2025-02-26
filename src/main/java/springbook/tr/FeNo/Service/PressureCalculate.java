package springbook.tr.FeNo.Service;

import org.springframework.stereotype.Service;

@Service
public class PressureCalculate {

	public double calculate(double value) {
		double cmH2O = 0.0101972;

		return Math.round(value * cmH2O * 100.0) / 100.0; // 소수점 둘째 자리 반올림
	}
}
