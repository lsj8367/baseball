package io.github.lsj8367.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @Test
    @DisplayName("섯다의 광 유무를 파악이 가능")
    void sutdaValidationTest() {
        final Card card = new Card(1, true);
        assertThat(card).isEqualTo(new Card(1, true));
    }

    @Test
    @DisplayName("광이 없는 해당월도 생성이 가능하다.")
    void sdsdf() {
        new Card(1, false);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("1,3,8을 제외한 카드는 광이 있을 수 없다.")
    void normalCardNotKwang(final int month, boolean light) {
        Assertions.assertThatThrownBy(() -> new Card(month, light))
            .isInstanceOf(IllegalStateException.class);
    }

    private static Stream<Arguments> normalCardNotKwang() {
        return Stream.of(
            Arguments.of(2, true),
            Arguments.of(4, true),
            Arguments.of(5, true),
            Arguments.of(6, true),
            Arguments.of(7, true),
            Arguments.of(9, true),
            Arguments.of(10, true)
        );
    }

}
