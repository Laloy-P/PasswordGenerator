package papalima.passwordgenerator.model;

public enum Interval {

    LOWERCASE(97, 122),
    UPPERCASE(65, 90),
    NUMBERS(48, 57),
    SPECIAL_CHARS_1(33, 47),
    SPECIAL_CHARS_2(58, 64),
    SPECIAL_CHARS_3(91, 95),
    SPECIAL_CHARS_4(123, 126);

    private final int lowerBoundary;
    private final int upperBoundary;

    Interval(int lowerBoundary, int upperBoundary) {
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
    }

    /**
     * Verify if a number is contained inside the interval
     * @param number
     * @return
     */
    public boolean contains(int number) {
        return number >= lowerBoundary && number <= upperBoundary;
    }

    /**
     * Provide the range of a given interval. the upper and lower boundary are
     * inside the interval.
     * @return
     */
    public int getRange() {
        return upperBoundary - lowerBoundary + 1;
    }

    public int getLowerBoundary() {
        return lowerBoundary;
    }
}
