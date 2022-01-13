package io.github.lsj8367;

public class MatchGenerator {
    private final String correctNumber;
    private final String inputNumber;

    public MatchGenerator(final String correctNumber, final String inputNumber) {
        this.correctNumber = correctNumber;
        this.inputNumber = inputNumber;
    }

    public int strike() {
        int count = 0;
        for (int i = 0; i < correctNumber.length(); i++) {
            if (isMatchNumber(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isMatchNumber(final int i) {
        return correctNumber.charAt(i) == inputNumber.charAt(i);
    }

    public int ball() {
        int count = 0;
        for (int i = 0; i < correctNumber.length(); i++) {
            if (isContainsNumber(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isContainsNumber(final int i) {
        return (correctNumber.charAt(i) != inputNumber.charAt(i))
            && correctNumber.contains(String.valueOf(inputNumber.charAt(i)));
    }

}
