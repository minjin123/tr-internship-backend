package springbook.tr.FeNo.Service;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springbook.tr.exception.BusinessException;
import springbook.tr.exception.ErrorCode;

@Service
public class MeasurementFileReader {

	public String readFile(MultipartFile file) {
		try {
			String content = new String(file.getBytes(), StandardCharsets.UTF_8);
			if (content.isEmpty()) {
				throw new BusinessException(ErrorCode.NULL_OR_EMPTY);
			}
			return formatRawData(content);
		} catch (IOException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	private String formatRawData(String rawData) {
		StringBuilder sb = new StringBuilder();
		String[] chunks = rawData.split("N");
		for (String chunk : chunks) {
			if (!chunk.isBlank()) {
				sb.append(chunk.trim()).append("\n");
			}
		}
		return sb.toString().trim();

	}
}