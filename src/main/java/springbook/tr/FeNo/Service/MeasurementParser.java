package springbook.tr.FeNo.Service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import springbook.tr.FeNo.model.entity.Measurement;
import springbook.tr.patient.model.entity.Patient;

@Service
@RequiredArgsConstructor
public class MeasurementParser {

	private final NitricOxideCalculate nitricOxideCalculate;
	private final PressureCalculate pressureCalculate;

	public Measurement parseMeasurement(String line, String rawContent, Patient patient) {
		String[] values = line.replace("N", "").trim().split("\\s+");
		if (values.length != 3) {
			return null;
		}
		BigDecimal nitricOxide = nitricOxideCalculate.calculateScaled(new BigDecimal(values[1]));
		BigDecimal pressure = pressureCalculate.calculate(new BigDecimal(values[2]));


		return Measurement.builder()
			.patient(patient)
			.rawContent(rawContent)
			.flowRate(new BigDecimal(values[0]))
			.nitricOxide(nitricOxide)
			.pressure(pressure)
			.build();
	}
}
