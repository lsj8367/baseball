package io.github.lsj8367.util;

import java.util.Arrays;

public class NumberValidator {

    private NumberValidator() {
    }

    public static String validation(final String number) {

        if (number == null || number.isBlank()) {
            throw new IllegalStateException("문자열이 비어있거나 null일 수 없습니다.");
        }

        if (number.charAt(0) == '0') {
            throw new IllegalArgumentException("0을 포함해서는 안됩니다.");
        }

        long letterLength = Arrays.stream(number.split(""))
            .distinct().count();

        if (letterLength != 3) {
            throw new IllegalArgumentException("숫자는 중복되지 않는 3자리여야 합니다.");
        }

        return number;
    }

}
