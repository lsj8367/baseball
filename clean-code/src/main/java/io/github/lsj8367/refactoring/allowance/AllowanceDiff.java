package io.github.lsj8367.refactoring.allowance;

import io.github.lsj8367.refactoring.Log;
import java.math.BigDecimal;

public record AllowanceDiff(BigDecimal allowanceDiff, BigDecimal allowanceDiffPerc) {

    public boolean isMinusValue() {
        return allowanceDiff.abs()
            .compareTo(AllowanceConfig.LIMIT.getValue()) < 1;
    }

    public void differenceLog() {
        Log.logApplicationInfo("Allowance Diff : " + allowanceDiff.doubleValue(),
            allowanceDiff, getClass());
        Log.logApplicationInfo("Allowance Diff Perc : "
                + allowanceDiffPerc.doubleValue(), allowanceDiffPerc,
            getClass());
    }

    public boolean isPercentLessConfig(final Allowance allowance) {
        // 구성된 허용 한도 값 및 허용 차이 백분율 가져오기
        if (allowance.validAllowance()) {
            return allowanceDiffPerc.compareTo(AllowanceConfig.PERCENT.getValue()) < 1;
        }
        return false;
    }

}
