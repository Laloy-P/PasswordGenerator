package papalima.passwordgenerator.model;

public record IntervalRecord(int minValue, int maxValue) {
    public int getRange() {
        return maxValue - minValue + 1;
    }
}
