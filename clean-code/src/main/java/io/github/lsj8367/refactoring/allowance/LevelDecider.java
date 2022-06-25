package io.github.lsj8367.refactoring.allowance;

import io.github.lsj8367.refactoring.Log;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


public class LevelDecider {

    public String determineLevel(final File fileVersion,
        final long fileVersionIdPrev, final Allowance allowance, final BigDecimal inputAllowance) {
        boolean isAllowanceDiffPercLessConfig = false;

        if (fileVersion.isFileClosed()) {
            return Constants.LEVEL_D2;
        }

        if (inputAllowance != null && fileVersionIdPrev != 0
            && allowance.getAllowanceId() != 0) {
            BigDecimal allowancePrevVal = allowance.getAllowanceValue();
            Log.debug("Previous Allowance value = # {0}", allowancePrevVal);

            // calculate Allowance Diff
            BigDecimal allowanceDiff = inputAllowance.subtract(allowancePrevVal);
            Log.debug("Allowance Difference value = # {0}", allowanceDiff);

            // calculate Allowance Diff Percentage

            final BigDecimal allowanceDiffPerc = calculateAllowanceDiffPercent(allowancePrevVal, allowanceDiff);

            // Get configured Allowance limit value & allowance difference percentage
            if (allowancePrevVal != null && allowancePrevVal.doubleValue() != 0) {
                isAllowanceDiffPercLessConfig = allowanceDiffPerc
                    .compareTo(AllowanceConfig.PERCENT.getValue()) < 1;
            }



            Log.logApplicationInfo(
                "Allowance Diff : " + allowanceDiff.doubleValue(),
                allowanceDiff, getClass());
            Log.logApplicationInfo("Allowance Diff Perc : "
                    + allowanceDiffPerc.doubleValue(), allowanceDiffPerc,
                getClass());

            if (allowanceDiff.abs().compareTo(AllowanceConfig.LIMIT.getValue()) < 1
                || isAllowanceDiffPercLessConfig) {
                return Constants.LEVEL_D1;
            }

            return Constants.LEVEL_D2;
        }

        // if prev file  or allowance for previous file
        // does not exsits
        // then  level is D2
        return Constants.LEVEL_D2;
    }

    private BigDecimal calculateAllowanceDiffPercent(final BigDecimal allowancePrevVal, final BigDecimal allowanceDiff) {
        if (Objects.isNull(allowancePrevVal) || allowancePrevVal.doubleValue() == 0) {
            return Constants.BIG_DECIMAL_ZERO;
        }

        return allowanceDiff.abs()
            .divide(allowancePrevVal, 2, RoundingMode.HALF_UP)
            .multiply(Constants.BIG_DECIMAL_HUNDRED);
    }

}