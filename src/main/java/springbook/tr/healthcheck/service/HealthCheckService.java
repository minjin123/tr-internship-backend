package springbook.tr.healthcheck.service;

import springbook.tr.healthcheck.model.HealthCheckResponse;
import springbook.tr.healthcheck.model.HealthStatus;

public class HealthCheckService {

	public static HealthCheckResponse getServerHealthStatus() {
		return new HealthCheckResponse(HealthStatus.UP);
	}
}
