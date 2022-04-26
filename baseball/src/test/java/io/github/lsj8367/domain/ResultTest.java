package io.github.lsj8367.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    @DisplayName("아웃인 경우")
    void outTest() {
        final Result result = new Result(0, 0);
        assertThat(result.isOut()).isTrue();
    }

    @Test
    @DisplayName("전부 스트라이크만 존재하는 경우")
    void onlyStrike() {
        final Result result = new Result(1, 0);
        assertThat(result.isOnlyStrike()).isTrue();
    }

    @Test
    @DisplayName("볼만 존재하는 경우")
    void onlyBall() {
        final Result result = new Result(0, 2);
        assertThat(result.isOnlyBall()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 볼 둘다 존재하는 경우")
    void bothExist() {
        final Result result = new Result(1, 2);
        assertThat(result.isStrikeAndBall()).isTrue();
    }

}
