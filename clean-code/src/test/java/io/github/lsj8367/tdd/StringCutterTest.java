package io.github.lsj8367.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringCutterTest {

    private StringCutter stringCutter;

    @BeforeEach
    void setUp() {
        stringCutter = new StringCutter();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("앞 두글자중 A가 있으면 제거")
    void ifFirstTwoCharactersAreAThenCut(final String actual, final String expected) {
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> ifFirstTwoCharactersAreAThenCut() {
        final StringCutter stringCutter = new StringCutter();
        return Stream.of(
            Arguments.of(stringCutter.removeAFirstTwoCharacter("AACD"), "CD"),
            Arguments.of(stringCutter.removeAFirstTwoCharacter("ABCD"), "BCD"),
            Arguments.of(stringCutter.removeAFirstTwoCharacter("BACD"), "BCD"),
            Arguments.of(stringCutter.removeAFirstTwoCharacter("MNAA"), "MNAA"),
            Arguments.of(stringCutter.removeAFirstTwoCharacter("AAAA"), "AA")
        );
    }

    @Test
    @DisplayName("앞 두글자중 A가 없으면 그대로 유지")
    void ifNotFirstTwoCharactersAreAThenKeep() {
        assertThat(stringCutter.removeAFirstTwoCharacter("BDAA")).isEqualTo("BDAA");
    }

    @Test
    @DisplayName("문자열의 총길이가 2보다 작은경우면 A를 전부 빈 문자열로 변환")
    void stringLengthLessThan2ThenConvertEmptyStr() {
        assertThat(stringCutter.removeAFirstTwoCharacter("A")).isEmpty();
    }
}
