package io.github.lsj8367.design.business.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.lsj8367.design.business.text.TextHelper;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TextHelperTest {

    private final TextHelper helper = new TextHelper();

    @Test
    @DisplayName("문자열을 null 넣을 시 에러 발생")
    void nullStrTest() {
        assertThatThrownBy(() -> helper.swapLastTwoCharacters(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("문자열은 비어있을 수 없습니다.");
    }

    private static Stream<Arguments> truncateAInFirst2PositionsTest() {
        final TextHelper helper = new TextHelper();
        return Stream.of(
            Arguments.of(helper.truncateAInFirst2Positions("ABCD"), "BCD"),
            Arguments.of(helper.truncateAInFirst2Positions("AACD"), "CD"),
            Arguments.of(helper.truncateAInFirst2Positions("BACD"), "BCD"),
            Arguments.of(helper.truncateAInFirst2Positions("BBAA"), "BBAA"),
            Arguments.of(helper.truncateAInFirst2Positions("bbaa"), "BBAA"),
            Arguments.of(helper.truncateAInFirst2Positions("abab"), "BAB")
        );
    }

    @Test
    @DisplayName("문자열 그대로 반환")
    void returnSelfTest() {
        assertThat(helper.swapLastTwoCharacters("A")).isEqualTo("A");
    }

    @Test
    @DisplayName("길이가 2 이상이면 두 문자를 스왑")
    void ifGreaterThan2ThenSwap() {
        assertThat(helper.swapLastTwoCharacters("AB")).isEqualTo("BA");
    }

    @Test
    @DisplayName("뒤의 2개의 문자만 스왑")
    void back2CharSwap() {
        assertThat(helper.swapLastTwoCharacters("RAIN")).isEqualTo("RANI");
    }

    @Test
    @DisplayName("빈문자열이면 자기자신을 반환")
    void emptyStringReturnSelf() {
        assertThat(helper.swapLastTwoCharacters("")).isEmpty();
    }

    @Test
    @DisplayName("null 문자열이 오면 예외 테스트")
    void nullStringExceptionTest() {
        assertThatThrownBy(() -> helper.truncateAInFirst2Positions(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("문자열은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("빈문자열을 그대로 반환")
    void emptyStringReturnTest() {
        assertThat(helper.truncateAInFirst2Positions("")).isEmpty();
    }

    @Test
    @DisplayName("문자열 길이 2 미만인 경우 A를 빈문자열로 변환")
    void ifLessThan2ThenReplaceBlank() {
        assertThat(helper.truncateAInFirst2Positions("A")).isEmpty();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("케이스 전부 테스트")
    void truncateAInFirst2PositionsTest(final String actual, final String expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
