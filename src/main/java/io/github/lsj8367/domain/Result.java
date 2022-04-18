package io.github.lsj8367.domain;

public class Result {

    private final int strike;
    private final int ball;
    private final int out;

    public Result(final int strike, final int ball, final int out) {
        outValidation(strike, ball, out);
        this.strike = strike;
        this.ball = ball;
        this.out = out;
    }

    private void outValidation(final int strike, final int ball, final int out) {
        if (out == 0) {
            return;
        }

        if (out != 3) {
            throw new IllegalArgumentException("아웃 카운트는 3밖에 올 수 없습니다.");
        }

        if (strike != 0 || ball != 0) {
            throw new IllegalArgumentException("아웃이 존재하는 경우 strike와 ball은 횟수가 있을 수 없습니다.");
        }
    }

    public boolean isOut() {
        return out == 3 && strike == 0 && ball == 0;
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

}
