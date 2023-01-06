package io.github.lsj8367;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

record StringCalculator() {

    public int calculate(final String text) {
        if (text.isBlank()) {
            return 0;
        }
        return sum(split(text));
    }

    private int sum(final String[] numbers) {
        return Arrays.stream(numbers)
            .mapToInt(this::toPositive)
            .sum();
    }

    private int toPositive(final String number) {
        final int value = Integer.parseInt(number);
        if (value < 0) {
            throw new IllegalArgumentException("음수는 될 수 없습니다.");
        }
        return value;
    }

    private String[] split(final String text) {
        final Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        if (m.find()) {
            final String group = m.group(1);
            return m.group(m.groupCount()).split(group);
        }

        return text.split("[,:]");
    }

}
