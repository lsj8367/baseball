package io.github.lsj8367.refactoring.allowance;

import io.github.lsj8367.refactoring.Log;
import java.math.BigDecimal;

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

    public BigDecimal getAllowanceValue() {
        return allowanceValue;
    }

    public void setAllowanceId(int allowanceId) {
        this.allowanceId = allowanceId;
    }

    public void setAllowanceValue(BigDecimal allowanceValue) {
        this.allowanceValue = allowanceValue;
    }

    public boolean validAllowance() {
        return allowanceValue != null && allowanceValue.doubleValue() != 0;
    }

    public BigDecimal getPreviousValue() {
        Log.debug("Previous Allowance value = # {0}", allowanceValue);
        return allowanceValue;
    }

    public BigDecimal valueDifference(final BigDecimal inputAllowance) {
        final BigDecimal allowanceDiff = inputAllowance.subtract(allowanceValue);
        Log.debug("Allowance Difference value = # {0}", allowanceDiff);
        return allowanceDiff;
    }

}
