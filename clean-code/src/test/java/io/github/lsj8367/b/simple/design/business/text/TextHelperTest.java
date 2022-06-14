package io.github.lsj8367.b.simple.design.business.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TextHelperTest {

    private TextHelper helper = new TextHelper();

    @Test
    @DisplayName("문자열을 null 넣을 시 에러 발생")
    void nullStrTest() {
        assertThatThrownBy(() -> helper.swapLastTwoCharacters(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("문자열은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("빈문자열이면 자기자신을 반환")
    void emptyStringReturnSelf() {
        assertThat(helper.swapLastTwoCharacters("")).isEqualTo("");
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
    @Disabled
    public void testTruncateAInFirst2Positions() {
        assertEquals("", helper.truncateAInFirst2Positions(""));
        assertEquals("BCD", helper.truncateAInFirst2Positions("ABCD"));
        assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
        assertEquals("BCD", helper.truncateAInFirst2Positions("BACD"));
        assertEquals("BBAA", helper.truncateAInFirst2Positions("BBAA"));
    }

}
