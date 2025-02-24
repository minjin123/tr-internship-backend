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
import springbook.tr.exception.BusinessException;
import springbook.tr.exception.CustomException;
import springbook.tr.exception.ErrorCode;
import springbook.tr.patient.model.entity.Patient;
import springbook.tr.patient.model.repository.PatientRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementService {

	private final MeasurementRepository measurementRepository;
	private final PatientRepository patientRepository;

	private final MeasurementParser measurementParser;
	private final MeasurementFileReader measurementFileReader;

	public MeasurementResponseDto processAndSaveMeasurements(MultipartFile file, Long patientId) {
		Patient patient = findPatientById(patientId);
		List<String> lines = measurementFileReader.readFile(file);
		String rawContent = String.join("\n", lines);
		List<Measurement> measurements = new ArrayList<>();
		addMeasurementValue(lines, patient, rawContent, measurements);
		measurementRepository.saveAll(measurements);

		return createAverageFeNoResponse(measurements);
	}

	private void addMeasurementValue(List<String> lines, Patient patient, String rawContent,
		List<Measurement> measurements) {
		for (String line : lines) {
			Measurement measurement = measurementParser.parseMeasurement(line, rawContent, patient);
			if (measurement != null) {
				measurements.add(measurement);
			}
		}
	}


	private Patient findPatientById(Long patientId) {
		return patientRepository.findById(patientId)
			.orElseThrow(() ->new CustomException(ErrorCode.PATIENT_NOT_FOUND));
	}

	private MeasurementResponseDto createAverageFeNoResponse(List<Measurement> measurements) {
		BigDecimal averageFeNo = createAverageFeNo(measurements);

		return MeasurementResponseDto.builder()
			.nitricOxide(averageFeNo)
			.build();
	}

	private BigDecimal createAverageFeNo(List<Measurement> measurements) {
		if ((measurements == null) || measurements.isEmpty()) {
			throw new BusinessException(ErrorCode.NULL_OR_EMPTY);
		}
		BigDecimal totalFeNo = measurements.stream()
			.map(Measurement::getNitricOxide)
			.reduce(BigDecimal.ZERO, BigDecimal::add);

		return totalFeNo.divide(
			BigDecimal.valueOf(measurements.size()), 2, RoundingMode.HALF_UP
		);
	}


}