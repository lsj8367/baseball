package io.github.lsj8367;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringCalculatorTest {

    private final StringCalculator calculator = new StringCalculator();

    @Test
    void zeroTest() {
        assertThat(calculator.calculate(" ")).isZero();
    }

    @MethodSource
    @ParameterizedTest
    void plusTest(final String text, final int expected) {
        assertThat(calculator.calculate(text)).isEqualTo(expected);
    }

    private static Stream<Arguments> plusTest() {
        return Stream.of(
            Arguments.of("1", 1),
            Arguments.of("1,2", 3),
            Arguments.of("1,2,3", 6),
            Arguments.of("1,2:3", 6)
        );
    }

    @Test
    void splitTest() {
        assertThat(calculator.calculate("//;\n1;2;3;4")).isEqualTo(10);
    }

    @Test
    void negativeExceptionTest() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> calculator.calculate("-1,2,3"))
            .withMessage("음수는 될 수 없습니다.");
    }

}
