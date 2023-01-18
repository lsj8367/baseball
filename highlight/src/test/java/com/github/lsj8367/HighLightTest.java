package com.github.lsj8367;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class HighLightTest {

    @ParameterizedTest
    @ValueSource(strings = {"가나다", "asaa11###", "ㄱㄱㄱ"})
    void inputExceptionTest(final String input) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> highLight(input))
            .withMessage("숫자와 알파벳 소문자, 공백만 입력이 가능합니다.");
    }

    @ParameterizedTest
    @MethodSource
    void highLightTest(final String input, final String expected) {
        //no a note -> no a {note}
        assertThat(highLight(input)).isEqualTo(expected);
    }

    private static Stream<Arguments> highLightTest() {
        return Stream.of(
            Arguments.of("abc", "abc"),
            Arguments.of("asdasda", "asdasda"),
            Arguments.of("note", "{note}"),
            Arguments.of("1 note", "1 {note}"),
            Arguments.of("1 note 2", "1 {note} 2"),
            Arguments.of("keynote", "keynote"),
            Arguments.of("ke1note", "ke1note"),
            Arguments.of("yes note1", "yes note1"),
            Arguments.of("yes notea", "yes notea"),
            Arguments.of("no a note", "no a {note}"),
            Arguments.of("no a note note", "no a {note} {note}"),
            Arguments.of("note anote note note note", "{note} anote {note} {note} {note}"),
            Arguments.of("no a note note anote", "no a {note} {note} anote")
        );
    }

    private String highLight(String str) {
        inputValid(str);

        final StringBuilder result = new StringBuilder();
        while (true) {
            //루프 순회하면서 str을 재구성해야한다.
            final int index = str.indexOf("note");

            if (index == -1) {
                result.append(str);
                break;
            }

            if (isLeft(str, index) || isRight(str, index)) {
                result.append(str, 0, index + "node".length());
            } else {
                final String frontStr = index > 0 ? str.substring(0, index) : "";
                result.append(frontStr).append("{note}");
            }
            str = str.substring(index + "note".length());

        }

        return result.toString();
    }

    private boolean isLeft(final String str, final int index) {
        if (index - 1 >= 0) {
            final char c = str.charAt(index - 1);
            return isNotHighlight(c);
        }
        return false;
    }

    private boolean isRight(final String input, final int index) {
        if (index + 4 < input.length()) {
            final char c = input.charAt(index + 4);
            return isNotHighlight(c);
        }
        return false;
    }

    private boolean isNotHighlight(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    private void inputValid(final String input) {
        final String regex = "^[a-z0-9 ]*$";
        if (!Pattern.matches(regex, input)) {
            throw new IllegalArgumentException("숫자와 알파벳 소문자, 공백만 입력이 가능합니다.");
        }
    }

}
