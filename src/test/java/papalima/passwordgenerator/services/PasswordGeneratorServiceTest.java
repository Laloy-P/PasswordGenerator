package papalima.passwordgenerator.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import papalima.passwordgenerator.model.Interval;
import papalima.passwordgenerator.model.PasswordRecord;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class PasswordGeneratorServiceTest {

    @InjectMocks
    PasswordGeneratorService passwordGeneratorService;

    @Test
    void generateRandomSizedAsciiIntegerArrayTest() {
        // Test if the generated array is within the expected boundaries
        Integer[] asciiArray = passwordGeneratorService.generateRandomSizedAsciiIntegerArray();
        int arraySize = asciiArray.length;
        assertTrue(arraySize >= 8 && arraySize <= 18);

        // Test if all values are within ASCII range
        for (Integer asciiValue : asciiArray) {
            assertTrue(asciiValue >= 33 && asciiValue <= 126);
        }
    }

    @Test
    void generatePasswordTest() {
        // Test if PasswordRecord is generated
        PasswordRecord passwordRecord = passwordGeneratorService.generatePassword();
        assertTrue(passwordRecord.password().length() >= 8 && passwordRecord.password().length() <= 18);
    }

    @Test
    void generatePasswordWithParamTest() {
        // Test if PasswordRecord is generated with parameters
        PasswordRecord passwordRecord = passwordGeneratorService.generatePasswordWithParam(10, true, true, true);
        assertEquals(10, passwordRecord.password().length());
    }

    @Test
    void convertAsciiArrayTest() {
        // Test if Integer array is correctly converted to String
        Integer[] asciiArray = {65, 66, 67};
        String expectedPassword = "ABC";
        assertEquals(expectedPassword, passwordGeneratorService.convertAsciiArray(asciiArray));
    }

    @Test
    void getIntervalsTest() {
        // Test if intervals are correctly retrieved based on parameters
        List<Interval> intervals = passwordGeneratorService.getIntervals(true, true, true);
        assertEquals(7, intervals.size());
        assertEquals(Interval.SPECIAL_CHARS_1, intervals.get(3));
        assertEquals(Interval.SPECIAL_CHARS_2, intervals.get(4));
        assertEquals(Interval.SPECIAL_CHARS_3, intervals.get(5));
        assertEquals(Interval.SPECIAL_CHARS_4, intervals.get(6));
    }

    @Test
    void generateRandomNumberTest() {
        // Test if random number is generated within specified intervals
        List<Interval> intervals = new ArrayList<>();

        intervals.add(Interval.NUMBERS);
        intervals.add(Interval.UPPERCASE);
        int randomNumber = passwordGeneratorService.generateRandomNumber(intervals);
        assertTrue(randomNumber >= 48 && randomNumber <= 90);
    }
}