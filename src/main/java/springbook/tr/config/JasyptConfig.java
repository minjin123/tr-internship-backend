package springbook.tr.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

	@Bean
	public PooledPBEStringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(cryptConfig());
		return encryptor;
	}

	@Bean
	public EnvironmentStringPBEConfig cryptConfig() {
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		String password = System.getenv("JASYPT_ENCRYPTOR_PASSWORD");
		if (password == null || password.isEmpty()) {
			throw new IllegalStateException("환경 변수 JASYPT_ENCRYPTOR_PASSWORD가 설정되지 않았습니다.");
		}
		config.setPassword(password);
		config.setPoolSize(1);
		return config;
	}
}
