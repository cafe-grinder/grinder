package com.grinder;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EnableBatchProcessing
@ActiveProfiles("test")
class GrinderApplicationTests {

	@Test
	void contextLoads() {
	}

}
