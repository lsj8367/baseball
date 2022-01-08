package io.github.lsj8367;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BaseBallNumbersTest {

    private BaseBallNumbers baseBallNumbers;

    @BeforeEach
    void setUp() {
        baseBallNumbers = new BaseBallNumbers();
    }

    @Test
    @DisplayName("세자리 난수 만들기")
    void init() {
        baseBallNumbers.generate();
        assertThat(baseBallNumbers.getSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("세자리 난수 문자열 반환")
    void wsetset() {
        baseBallNumbers.generate();
        final String number = baseBallNumbers.createNumber();
        assertThat(number).hasSize(3);
    }

}