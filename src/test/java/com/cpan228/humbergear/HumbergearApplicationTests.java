package com.cpan228.humbergear;

import com.cpan228.humbergear.model.HardwareItem;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HumbergearApplicationTests {

	private static Validator validator;

	@BeforeAll
	static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void skuValidationAllowsMultiSegmentFormats() {
		HardwareItem item = new HardwareItem(
				"NX-GPU-5069",
				"GeForce RTX 5069 Gaming",
				"NVIDIA",
				"GPU",
				new BigDecimal("899.99"),
				15,
				"Toronto Hub - Zone A",
				"Next-gen graphics card with high-speed memory."
		);

		var violations = validator.validate(item);
		assertTrue(violations.isEmpty(), "Expected NX-GPU-5069 to be valid without violations");
	}

	@Test
	void skuValidationRejectsMalformedSku() {
		HardwareItem item = new HardwareItem(
				"invalid_sku",
				"GeForce RTX 5069 Gaming",
				"NVIDIA",
				"GPU",
				new BigDecimal("899.99"),
				15,
				"Toronto Hub - Zone A",
				"Next-gen graphics card with high-speed memory."
		);

		var violations = validator.validateProperty(item, "sku");
		assertFalse(violations.isEmpty(), "Expected lowercase invalid_sku to be rejected");
	}
}
