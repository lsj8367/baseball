package io.github.lsj8367.tdd;

import java.util.Objects;

public record StringCutter(String str) {

    private static final String CUT_TARGET = "A";
    private static final String BLANK = "";

    public StringCutter {
        if (Objects.isNull(str)) {
            throw new IllegalArgumentException("문자열은 null일 수 없습니다.");
        }
    }

    public String removeAFirstTwoCharacter() {

        if (str.length() < 2) {
            return str.replace(CUT_TARGET, BLANK);
        }

        final String front = str.substring(0, 2).replace(CUT_TARGET, BLANK);
        final String back = str.substring(2);
        return front + back;
    }
}
