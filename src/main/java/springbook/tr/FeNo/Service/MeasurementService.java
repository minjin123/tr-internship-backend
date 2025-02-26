package springbook.tr.FeNo.Service;

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

	private final MeasurementFileReader measurementFileReader;

	private final NitricOxideCalculate nitricOxideCalculate;
	private final PressureCalculate pressureCalculate;

	public MeasurementResponseDto processAndSaveMeasurements(MultipartFile file, Long patientId) {
		Patient patient = findPatientById(patientId);
		String rawContent  = measurementFileReader.readFile(file);
		List<Double> nitricOxideList = addNitricOxideValue(rawContent);
		List<Double> pressureList = addPressureValue(rawContent);

		double avgNitricOxide = avgNitricOxide(nitricOxideList);
		double maxNitricOxide = maxNitricOxide(nitricOxideList);
		double avgPressure = avgPressure(pressureList);

		saveMeasurement(patient, rawContent, avgNitricOxide, avgPressure);

		return createResponse(avgNitricOxide, maxNitricOxide, avgPressure);
	}

	private double avgNitricOxide(List<Double> nitricOxideList) {
		double sum = 0;
		for (double nitricOxide : nitricOxideList) {
			sum += nitricOxide;
		}
		double avg = sum / nitricOxideList.size();

		return Math.round(avg * 100.0) / 100.0;
	}

	private double avgPressure(List<Double> pressureList) {
		double sum = 0;
		for (double pressure : pressureList) {
			sum += pressure;
		}
		double avg = sum / pressureList.size();

		return Math.round(avg * 100.0) / 100.0;
	}

	private double maxNitricOxide(List<Double> nitricOxideList) {
		double max = Double.MIN_VALUE;
		for (double nitricOxide : nitricOxideList) {
			if (max < nitricOxide) {
				max = nitricOxide;
			}
		}
		return max;
	}

	private List<Double> addNitricOxideValue(String rawContent) {
		List<Double> values = new ArrayList<>();
		String[] lines = rawContent.split("\n");

		for (String line : lines) {
			String[] tokens = line.split("\\s+");
			if (tokens.length != 3) {
				throw new BusinessException(ErrorCode.INVALID_DATA_SIZE);
			}
			double value = Double.parseDouble(tokens[1]);
			values.add(nitricOxideCalculate.calculateScaled(value));
		}
		return values;
	}

	private List<Double> addPressureValue(String rawContent) {
		List<Double> values = new ArrayList<>();
		String[] lines = rawContent.split("\n");

		for (String line : lines) {
			String[] tokens = line.split("\\s+");
			if (tokens.length != 3) {
				throw new BusinessException(ErrorCode.INVALID_DATA_SIZE);
			}
			double value = Double.parseDouble(tokens[2]);
			values.add(pressureCalculate.calculate(value));
		}
		return values;
	}

	private Patient findPatientById(Long patientId) {
		return patientRepository.findById(patientId)
			.orElseThrow(() ->new CustomException(ErrorCode.PATIENT_NOT_FOUND));
	}

	private MeasurementResponseDto createResponse(double avgNitricOxide, double maxNitricOxide, double avgPressure) {
		return MeasurementResponseDto.builder()
			.avgNitricOxide(avgNitricOxide)
			.maxNitricOxide(maxNitricOxide)
			.avgPressure(avgPressure)
			.build();
	}

	private void saveMeasurement(Patient patient, String rawContent, double avgNitricOxide, double avgPressure) {
		Measurement measurement = Measurement.builder()
			.patient(patient)
			.rawContent(rawContent)
			.flowRate(0.0)
			.nitricOxide(avgNitricOxide)
			.pressure(avgPressure)
			.build();
		measurementRepository.save(measurement);
	}
}