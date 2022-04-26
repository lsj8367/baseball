package io.github.lsj8367.domain;

public record Result(int strike, int ball) {

    public boolean isOut() {
        return strike == 0 && ball == 0;
    }

    public boolean isOnlyStrike() {
        return strike >= 1 && ball == 0;
    }

    public boolean isOnlyBall() {
        return strike == 0 && ball >= 1;
    }

    public boolean isStrikeAndBall() {
        return strike >= 1 && ball >= 1;
    }

    public boolean isCorrect() {
        return strike == 3;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

}
