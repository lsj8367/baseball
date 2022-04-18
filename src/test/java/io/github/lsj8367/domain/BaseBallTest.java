package io.github.lsj8367.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import io.github.lsj8367.domain.BaseBall;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BaseBallTest {

    @Test
    @DisplayName("ball 생성")
    void createTest() {
        assertThatCode(() -> BaseBall.create(() -> "123")).doesNotThrowAnyException();
    }

}
