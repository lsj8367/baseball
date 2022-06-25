package io.github.lsj8367.refactoring.allowance;

import java.math.BigDecimal;

public enum AllowanceConfig {

    LIMIT(50000.00),
    PERCENT(10.00);

    private final BigDecimal value;

    AllowanceConfig(final double value) {
        this.value = BigDecimal.valueOf(value);
    }

    public BigDecimal getValue() {
        return value;
    }
}
