package papalima.passwordgenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import papalima.passwordgenerator.model.Interval;
import papalima.passwordgenerator.model.IntervalRecord;
import papalima.passwordgenerator.services.PasswordGeneratorService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PasswordPasswordGeneratorServiceApplicationTests {
	private PasswordGeneratorService service;
	private List<Interval> intervals;

	@BeforeEach
	void setUp() {
		service = new PasswordGeneratorService();
		intervals = new ArrayList<>();
	}

	@Test
	void testGenerateRandomNumber_SingleInterval() {
		intervals.add(Interval.NUMBERS);

		int result = service.generateRandomNumber(intervals);

		Assertions.assertTrue(result >= 48 && result <= 57);
	}

	@Test
	void testGenerateRandomNumber_MultipleIntervals() {
		intervals.add(Interval.LOWERCASE);
		intervals.add(Interval.UPPERCASE);
		intervals.add(Interval.NUMBERS);

		int result = service.generateRandomNumber(intervals);

		Assertions.assertTrue(result >= 48 && result <= 122);
	}


}
