package io.github.lsj8367.refactoring.allowance;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LevelDeciderTest {

    private File file;

    @BeforeEach
    void setUp() {
        file = new File();
    }

    @Test
    void notAFKAllowanceDiffLessThanConfigured() {
        file.setPrevFileVersionStatus("TEST_VALUE_NOT_CLOSED");
        final LevelDecider levelDecider = new LevelDecider(file, 10,
            new Allowance(10, new BigDecimal("1000.00")), new BigDecimal("1100.00"));
        assertThat(levelDecider.determineLevel()).isEqualTo(Constants.LEVEL_D1);
    }

    @Test
    void notAFKAllowanceDiffPercGreaterThanConfigured() {
        file.setPrevFileVersionStatus("TEST_VALUE_NOT_AFK");
        final LevelDecider levelDecider = new LevelDecider(file, 10,
            new Allowance(10, new BigDecimal("4200000.00")), new BigDecimal("5000000.00"));
        assertThat(levelDecider.determineLevel()).isEqualTo(Constants.LEVEL_D2);
    }

    @Test
    void notAFKAllowanceDiffPercLessThanConfigured() {
        file.setPrevFileVersionStatus("TEST_VALUE_NOT_CLOSED");
        final LevelDecider levelDecider = new LevelDecider(file, 10,
            new Allowance(10, new BigDecimal("4600000.00")), new BigDecimal("5000000.00"));
        assertThat(levelDecider.determineLevel()).isEqualTo(Constants.LEVEL_D1);
    }

    @Test
    void notAFKAllowanceDiffZero() {
        file.setPrevFileVersionStatus("TEST_VALUE_NOT_CLOSED");
        final LevelDecider levelDecider = new LevelDecider(file, 10, new Allowance(10, new BigDecimal("1000.00")),
            new BigDecimal("1000.00"));
        assertThat(levelDecider.determineLevel()).isEqualTo(Constants.LEVEL_D1);
    }

    @Test
    void previousFileVerisonAFK() {
        file.setPrevFileVersionStatus(Constants.FILE_STATUS_CLOSED);
        final LevelDecider levelDecider = new LevelDecider(file, 10, null, null);
        assertThat(levelDecider.determineLevel()).isEqualTo(Constants.LEVEL_D2);
    }

    @Test
    void previousFileVerisonNotAFKAndAllowancesNull() {
        file.setPrevFileVersionStatus("TEST_VALUE_NOT_CLOSED");
        final LevelDecider levelDecider = new LevelDecider(file, 10, null, null);
        assertThat(levelDecider.determineLevel()).isEqualTo(Constants.LEVEL_D2);
    }

}