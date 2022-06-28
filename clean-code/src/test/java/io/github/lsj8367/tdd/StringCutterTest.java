package io.github.lsj8367.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringCutterTest {

    @Test
    @DisplayName("값 객체 비교")
    void isSameObject() {
        final StringCutter cutter = new StringCutter("");
        assertThat(cutter).isEqualTo(new StringCutter(""));
    }

    @Test
    @DisplayName("문자열에 null을 넣은 경우 예외")
    void strNullThenThrowException() {
        assertThatThrownBy(() -> new StringCutter(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("문자열은 null일 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("앞 두글자중 A가 있으면 제거")
    void ifFirstTwoCharactersAreAThenCut(final String actual, final String expected) {
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> ifFirstTwoCharactersAreAThenCut() {
        return Stream.of(
            Arguments.of(new StringCutter("AACD").removeAFirstTwoCharacter(), "CD"),
            Arguments.of(new StringCutter("ABCD").removeAFirstTwoCharacter(), "BCD"),
            Arguments.of(new StringCutter("BACD").removeAFirstTwoCharacter(), "BCD"),
            Arguments.of(new StringCutter("MNAA").removeAFirstTwoCharacter(), "MNAA"),
            Arguments.of(new StringCutter("AAAA").removeAFirstTwoCharacter(), "AA")
        );
    }

    @Test
    @DisplayName("앞 두글자중 A가 없으면 그대로 유지")
    void ifNotFirstTwoCharactersAreAThenKeep() {
        final StringCutter stringCutter = new StringCutter("BDAA");

        assertThat(stringCutter.removeAFirstTwoCharacter()).isEqualTo("BDAA");
    }

    @Test
    @DisplayName("문자열의 총길이가 2보다 작은경우면 A를 전부 빈 문자열로 변환")
    void stringLengthLessThan2ThenConvertEmptyStr() {
        final StringCutter stringCutter = new StringCutter("A");

        assertThat(stringCutter.removeAFirstTwoCharacter()).isEmpty();
    }

    @Test
    @DisplayName("문자열 길이가 2보다 작은 경우 무조건 false 반환")
    void stringLengthLessThan2ThenReturnFalse() {
        final StringCutter cutter = new StringCutter("A");
        assertThat(cutter.isSameFirst2AndLast2()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"AAA", "AB", "ABCAB"})
    @DisplayName("문자열 앞2글자 뒤2글자가 같으면 true")
    void stringFront2Last2AreSameThenReturnTrue(final String str) {
        final StringCutter cutter = new StringCutter(str);
        assertThat(cutter.isSameFirst2AndLast2()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC", "ABCDEBA"})
    @DisplayName("문자열 앞2글자 뒤2글자가 같으면 true")
    void stringFront2Last2AreSameThenReturnFalse(final String str) {
        final StringCutter cutter = new StringCutter(str);
        assertThat(cutter.isSameFirst2AndLast2()).isFalse();
    }
}
