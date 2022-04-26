package io.github.lsj8367.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatcherTest {

    @Test
    @DisplayName("볼만 존재하는 경우")
    void init() {
        final Result result = initData("345");
        assertIsTrue(result.isOnlyBall());
    }

    @Test
    @DisplayName("아웃인 경우")
    void outInit() {
        final Result result = initData("456");
        assertIsTrue(result.isOut());
    }

    @Test
    @DisplayName("스트라이크만 있는 경우")
    void strikeTest() {
        final Result result = initData("125");
        assertIsTrue(result.isOnlyStrike());
    }

    @Test
    @DisplayName("스트라이크 볼 같이 존재하는 경우")
    void bothExist() {
        final Result result = initData("213");
        assertIsTrue(result.isStrikeAndBall());
    }

    @Test
    @DisplayName("정답인 경우")
    void isCorrect() {
        final Result result = initData("123");
        assertIsTrue(result.isCorrect());
    }

    private void assertIsTrue(final boolean result) {
        assertThat(result).isTrue();
    }

    private Result initData(final String inputString) {
        final Matcher matcher = new Matcher();
        return matcher.checkNumber(BaseBall.create(() -> "123"), BaseBall.inputDataSet(inputString));
    }

}
