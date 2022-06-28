package io.github.lsj8367.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringCutterTest {

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
}
