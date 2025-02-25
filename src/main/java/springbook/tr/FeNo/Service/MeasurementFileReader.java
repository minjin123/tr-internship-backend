package springbook.tr.FeNo.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springbook.tr.exception.BusinessException;
import springbook.tr.exception.ErrorCode;

@Service
public class MeasurementFileReader {

	public String readFile(MultipartFile file) {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(
			new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(formatRawData(line)).append("\n");
			}
		} catch (IOException e) {
			throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
		return sb.toString().trim();
	}

	private String formatRawData(String rawData) {

		int chunkSize = 17;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < rawData.length(); i += chunkSize) {
			if (i + chunkSize <= rawData.length()) {
				String chunk = rawData.substring(i, i + chunkSize);
				chunk = chunk.replaceFirst("^N", "").trim();
				sb.append(chunk).append("\n");
			}
		}
		return sb.toString().trim();
	}
}