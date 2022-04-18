package io.github.lsj8367.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import io.github.lsj8367.domain.NumberGenerateStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomNumberGenerateStrategyTest {

    private final NumberGenerateStrategy strategy = new RandomNumberGenerateStrategy();

    @Test
    @DisplayName("난수 생성기 테스트")
    void generate() {
        final String numberString = strategy.generate();

        final String[] numbers = numberString.split("");
        assertThat(numbers).hasSize(3);
    }

}