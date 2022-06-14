package io.github.lsj8367.b.simple.design.business.text;

import java.util.Locale;
import java.util.Objects;

public class TextHelper {

    public String swapLastTwoCharacters(final String str) {
        inputValidation(str);

        if (str.length() < 2) {
            return str;
        }

        final char back = str.charAt(str.length() - 1);
        final char front = str.charAt(str.length() - 2);

        final String front2Chars = str.substring(0, str.length() - 2);

        return front2Chars + back + front;
    }

    private void inputValidation(final String str) {
        if (Objects.isNull(str)) {
            throw new IllegalArgumentException("문자열은 비어있을 수 없습니다.");
        }
    }

    public String truncateAInFirst2Positions(final String str) {
        inputValidation(str);

        final String upperStr = str.toUpperCase(Locale.ROOT);

        if (upperStr.length() < 2) {
            return removeA(upperStr);
        }

        final String front2Str = upperStr.substring(0, 2);

        final String eraseAStr = removeA(front2Str);

        return eraseAStr + upperStr.substring(2);
    }

    private String removeA(final String input) {
        return input.replace("A", "");
    }

}
