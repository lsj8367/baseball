package io.github.lsj8367.refactoring.allowance;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AllowanceDiffTest {

    @Test
    @DisplayName("차이값이 한계값보다 크면 false")
    void greaterThanLimitValueThenFalse() {
        final AllowanceDiff allowanceDiff = new AllowanceDiff(new BigDecimal("55000.00"), new BigDecimal("10.00"));
        assertThat(allowanceDiff.isMinusValue()).isFalse();
    }

    @Test
    @DisplayName("차이값이 한계값보다 작으면 true")
    void lessThanLimitValueThenTrue() {
        final AllowanceDiff allowanceDiff = new AllowanceDiff(new BigDecimal("49000.00"), new BigDecimal("10.00"));
        assertThat(allowanceDiff.isMinusValue()).isTrue();
    }

    @Test
    @DisplayName("수당 값이 1이상이고, percent가 10 아래면 true")
    void greaterThanPercentThenFalse() {
        final AllowanceDiff allowanceDiff = new AllowanceDiff(new BigDecimal("49000.00"), new BigDecimal("9.00"));
        assertThat(allowanceDiff.isPercentLessConfig(new Allowance(1, new BigDecimal("1.00")))).isTrue();
    }

}