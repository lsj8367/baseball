package io.github.lsj8367.b.simple.design.business.text;

import java.util.Objects;

public class TextHelper {

    public String swapLastTwoCharacters(final String str) {
        if (Objects.isNull(str)) {
            throw new IllegalArgumentException("문자열은 비어있을 수 없습니다.");
        }

        if (str.length() < 2) {
            return str;
        }

        final char back = str.charAt(str.length() - 1);
        final char front = str.charAt(str.length() - 2);

        final String front2Chars = str.substring(0, str.length() - 2);

        return front2Chars + back + front;
    }

    public String truncateAInFirst2Positions(String str) {
        return null;
    }

}
