package springbook.tr.FeNo.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import springbook.tr.FeNo.model.dto.MeasurementResponseDto;
import springbook.tr.FeNo.model.entity.Measurement;
import springbook.tr.FeNo.model.repository.MeasurementRepository;
import springbook.tr.patient.model.entity.Patient;
import springbook.tr.patient.model.repository.PatientRepository;

@Service
@RequiredArgsConstructor
public class MeasurementService {

	private final MeasurementRepository measurementRepository;
	private final PatientRepository patientRepository;

	private final MeasurementParser measurementParser;
	private final MeasurementFileReader measurementFileReader;

	@Transactional
	public MeasurementResponseDto processAndSaveMeasurements(MultipartFile file, Long patientId) {

		Patient patient = findPaintById(patientId);
		List<String> lines = measurementFileReader.readFile(file);
		List<Measurement> measurements = new ArrayList<>();
		addMeasurementValue(lines, patient, measurements);
		measurementRepository.saveAll(measurements);

		return createAverageFeNoResponse(measurements);
	}

	private void addMeasurementValue(List<String> lines, Patient patient, List<Measurement> measurements) {
		StringBuilder rawContentBuilder = new StringBuilder();
		for (String line : lines) {
			rawContentBuilder.append(line);
			Measurement measurement = measurementParser.parseMeasurement(line, rawContentBuilder.toString(), patient);
			rawContentBuilder.setLength(0);
			if (measurement != null) {
				measurements.add(measurement);
			}
		}
	}

	private Patient findPaintById(Long patientId) {
		return patientRepository.findById(patientId)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 환자를 찾을 수 없습니다: " + patientId));
	}

	private MeasurementResponseDto createAverageFeNoResponse(List<Measurement> measurements) {

		BigDecimal averageFeNo = createAverageFeNo(measurements);

		return MeasurementResponseDto.builder()
			.nitricOxide(averageFeNo)
			.build();
	}

	private BigDecimal createAverageFeNo(List<Measurement> measurements) {
		BigDecimal totalFeNo = measurements.stream()
			.map(Measurement::getNitricOxide)
			.reduce(BigDecimal.ZERO, BigDecimal::add);

		return totalFeNo.divide(
			BigDecimal.valueOf(measurements.size()), 2, RoundingMode.HALF_UP
		);
	}

}