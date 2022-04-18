package io.github.lsj8367.application;

import io.github.lsj8367.domain.BaseBall;
import io.github.lsj8367.domain.Matcher;
import io.github.lsj8367.domain.NumberGenerateStrategy;
import io.github.lsj8367.domain.Result;

public class GameProcess {

    private final Matcher matcher;

    private final BaseBall matchNumber;

    private GameProcess(final Matcher matcher, final BaseBall baseBall) {
        this.matcher = matcher;
        this.matchNumber = baseBall;
    }

    public static GameProcess run(final Matcher matcher, final NumberGenerateStrategy strategy) {
        return new GameProcess(matcher, BaseBall.create(strategy));
    }

    public Result guessNumber(final String guessNumber) {
        return matcher.checkNumber(matchNumber, BaseBall.inputDataSet(guessNumber));
    }

}
