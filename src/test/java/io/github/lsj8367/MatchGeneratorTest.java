package io.github.lsj8367;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchGeneratorTest {

    @Test
    @DisplayName("스트라이크 갯수 검사")
    void strikeCountTest() {
        MatchGenerator matchGenerator = new MatchGenerator("123", "134");
        assertThat(matchGenerator.strike()).isEqualTo(1);
    }

    @Test
    @DisplayName("볼 갯수 검사")
    void ballCountTest() {
        MatchGenerator matchGenerator = new MatchGenerator("123", "312");
        assertThat(matchGenerator.ball()).isEqualTo(3);
    }

}