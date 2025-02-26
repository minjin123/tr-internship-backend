package springbook.tr.FeNo.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import springbook.tr.FeNo.model.dto.MeasurementInquiryResponseDto;
import springbook.tr.FeNo.model.entity.Measurement;
import springbook.tr.FeNo.model.repository.MeasurementRepository;
import springbook.tr.exception.CustomException;
import springbook.tr.exception.ErrorCode;
import springbook.tr.patient.model.entity.Patient;
import springbook.tr.patient.model.repository.PatientRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementRecordInquiryService {
	private final MeasurementRepository measurementRepository;
	private final PatientRepository patientRepository;

	public List<MeasurementInquiryResponseDto> getMeasurementRecord(Long patientId, String date) {
		Patient patient = findPaintById(patientId);
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
		List<Measurement> measurements = measurementRepository.findByPatientAndCreatedAt(patient, localDate)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_DATE));


		return createMeasurementRecordResponse(measurements);
	}

	private Patient findPaintById(Long patientId) {
		return patientRepository.findById(patientId)
			.orElseThrow(() -> new CustomException(ErrorCode.PATIENT_NOT_FOUND));
	}

	private List<MeasurementInquiryResponseDto> createMeasurementRecordResponse(List<Measurement> measurements) {
		return measurements.stream()
			.map(MeasurementInquiryResponseDto::from)
			.collect(Collectors.toList());
	}
}
