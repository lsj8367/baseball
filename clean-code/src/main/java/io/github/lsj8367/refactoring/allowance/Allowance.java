package io.github.lsj8367.refactoring.allowance;

import io.github.lsj8367.refactoring.Log;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Allowance {

    int allowanceId;

    BigDecimal allowanceValue;

    public Allowance(int allowanceId, BigDecimal allowanceValue) {
        this.allowanceId = allowanceId;
        this.allowanceValue = allowanceValue;
    }

    public int getAllowanceId() {
        return allowanceId;
    }

    public boolean validAllowance() {
        return allowanceValue != null && allowanceValue.doubleValue() != 0;
    }

    public void previousValueLog() {
        Log.debug("Previous Allowance value = # {0}", allowanceValue);
    }

    public BigDecimal valueDifference(final BigDecimal inputAllowance) {
        // 수당 차액 계산
        final BigDecimal allowanceDiff = inputAllowance.subtract(allowanceValue);
        Log.debug("Allowance Difference value = # {0}", allowanceDiff);
        return allowanceDiff;
    }

    public BigDecimal calculateAllowanceDiffPercent(final BigDecimal allowanceDiff) {
        // 수당차이 백분율 계산
        if (Objects.isNull(allowanceValue) || allowanceValue.doubleValue() == 0) {
            return Constants.BIG_DECIMAL_ZERO;
        }

        return allowanceDiff.abs()
            .divide(allowanceValue, 2, RoundingMode.HALF_UP)
            .multiply(Constants.BIG_DECIMAL_HUNDRED);
    }

}
