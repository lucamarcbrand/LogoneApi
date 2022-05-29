package com.test.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopeWebAppWsApplicationTests {

	@Test
	public void insertNewPermanentEmp() {
		assertThat("hello").isNotEqualTo(null);
	}
}
