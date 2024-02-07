package papalima.passwordgenerator.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import papalima.passwordgenerator.model.Interval;
import papalima.passwordgenerator.model.PasswordRecord;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordGeneratorService {


    public PasswordRecord generatePassword() {
        Integer[] asciiArray = generateRandomSizedAsciiIntegerArray();

        return new PasswordRecord(convertAsciiArray(asciiArray));

    }

    public PasswordRecord generatePasswordWithParam(Integer size, Boolean specialChars, Boolean upperCase, Boolean withNumbers) {

        Integer[] asciiArray = generateFixedSizedAsciiIntegerArray(size, specialChars, upperCase, withNumbers);

        return new PasswordRecord(convertAsciiArray(asciiArray));
    }

    //
    /*
    Generate a random Integer Array with value that can match an alpha numeric value inside ascii table,
    value will be withing 33->126 range.
    lengh of the array will be between 8 and 18.
     */
     Integer[] generateRandomSizedAsciiIntegerArray() {
        Integer[] asciiArray;
        SecureRandom rand = new SecureRandom();
        int lowerArrayBoundary = 8;
        int upperArrayBoundary = 18;
        int lowerAsciibondary = 33;
        int upperAsciiBondary = 126;

        int arraySize = rand.nextInt(lowerArrayBoundary, upperArrayBoundary);
        asciiArray = new Integer[arraySize];

        for (int i = 0; i < arraySize; i++) {
            asciiArray[i] = rand.nextInt(lowerAsciibondary, upperAsciiBondary);
        }

        return asciiArray;
    }

    private Integer[] generateFixedSizedAsciiIntegerArray(int size, boolean specialChars, boolean upperCase, boolean withNumbers) {
        Integer[] asciiArray = new Integer[size];

        for (int i = 0; i < size; i++) {
            asciiArray[i] = generateRandomNumber(getIntervals(specialChars, upperCase, withNumbers));
        }

        return asciiArray;
    }

     String convertAsciiArray(Integer[] asciiArray) {
        StringBuilder password = new StringBuilder();

        for (Integer number : asciiArray) {
            char tmp = (char) number.intValue();
            password.append(tmp);
        }

        return password.toString();
    }

    public List<Interval> getIntervals(boolean specialChar, boolean upperCase, boolean withNumbers) {

        List<Interval> intervals = new ArrayList<>();

        // Default Intervals :
        intervals.add(Interval.LOWERCASE);// lowercase

        if (upperCase) intervals.add(Interval.UPPERCASE);// uppercase

        if (withNumbers) intervals.add(Interval.NUMBERS);// withNumber

        if (specialChar) {
            intervals.add(Interval.SPECIAL_CHARS_1);// specialChars
            intervals.add(Interval.SPECIAL_CHARS_2);// specialChars
            intervals.add(Interval.SPECIAL_CHARS_3);// specialChars
            intervals.add(Interval.SPECIAL_CHARS_4);// specialChars
        }

        return intervals;
    }

    /**
     * Generates a random number which is included among the intervals given as
     * a parameter.
     *
     * @param intervals interval list.
     * @return a random number included among the intervals given.
     */
    public int generateRandomNumber(List<Interval> intervals) {

        if (intervals.isEmpty()) return -1;

        int totalRange = 0;

        Assert.notEmpty(intervals, "Parameters empty : intervals in Method generateRandomNumber");

        // Calculer la plage totale couvrant tous les intervalles
        for (Interval interval : intervals) {
            totalRange += interval.getRange();
        }

        SecureRandom random = new SecureRandom();
        int randomNumber = random.nextInt(totalRange) + 1;

        // Parcourir les intervalles pour trouver celui qui contient le nombre généré
        for (Interval interval : intervals) {
            if (randomNumber <= interval.getRange()) {
                return interval.getLowerBoundary() + randomNumber - 1;
            }
            randomNumber -= interval.getRange();
        }

        return -1; // En cas d'erreur
    }
}
