package io.github.lsj8367.refactoring.allowance;

import java.math.BigDecimal;
import java.util.Objects;

public class LevelDecider {

    private final File fileVersion;
    private final long fileVersionIdPrev;
    private final Allowance allowance;
    private final BigDecimal inputAllowance;

    public LevelDecider(final File fileVersion, final long fileVersionIdPrev, final Allowance allowance, final BigDecimal inputAllowance) {
        this.fileVersion = fileVersion;
        this.fileVersionIdPrev = fileVersionIdPrev;
        this.allowance = allowance;
        this.inputAllowance = inputAllowance;
    }

    public String determineLevel() {

        if (isInitialValue()) {
            return Constants.LEVEL_D2;
        }

        allowance.previousValueLog();

        final BigDecimal allowanceDiff = allowance.valueDifference(inputAllowance);
        final BigDecimal allowanceDiffPerc = allowance.calculateAllowanceDiffPercent(allowanceDiff);

        final AllowanceDiff diff = new AllowanceDiff(allowanceDiff, allowanceDiffPerc);

        final boolean isLessConfig = diff.isPercentLessConfig(allowance);

        diff.differenceLog();

        if (diff.isMinusValue() || isLessConfig) {
            return Constants.LEVEL_D1;
        }

        // 이전 파일 또는 이전 파일에 대한 여유가 있는 경우
        // 존재하지 않는다.
        return Constants.LEVEL_D2;
    }

    private boolean isInitialValue() {
        if (fileVersion.isFileClosed()) {
            return true;
        }

        if (Objects.isNull(inputAllowance)) {
            return true;
        }

        if (fileVersionIdPrev == 0) {
            return true;
        }

        return allowance.getAllowanceId() == 0;
    }

}