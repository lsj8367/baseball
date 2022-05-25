package io.github.lsj8367.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberValidatorTest {

    @Test
    @DisplayName("빈 값 체크")
    void emptyValidation() {
        assertThatThrownBy(() -> NumberValidator.validation(" "))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("문자열이 비어있거나 null일 수 없습니다.");

        assertThatThrownBy(() -> NumberValidator.validation(null))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("문자열이 비어있거나 null일 수 없습니다.");
    }

    @Test
    @DisplayName("길이 체크")
    void lengthValid() {
        assertThatThrownBy(() -> NumberValidator.validation("12314"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("숫자는 중복되지 않는 3자리여야 합니다.");
    }

    @Test
    @DisplayName("맨 앞 숫자 0 예외")
    void frontZeroValue() {
        assertThatThrownBy(() -> NumberValidator.validation("012"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("0을 포함해서는 안됩니다.");
    }

    @Test
    @DisplayName("중복 숫자 예외")
    void duplicateNumberCheck() {
        assertThatThrownBy(() -> NumberValidator.validation("111"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("숫자는 중복되지 않는 3자리여야 합니다.");
    }

}