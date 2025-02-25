package springbook.tr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
	"spring.datasource.url=",
	"spring.datasource.username=",
	"spring.datasource.password=",
	"spring.jpa.database-platform="
})
class TrApplicationTests {
	@Test
	void contextLoads() {
		// 테스트 코드
	}
}
