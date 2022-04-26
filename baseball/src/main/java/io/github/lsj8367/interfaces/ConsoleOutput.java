package io.github.lsj8367.interfaces;

import io.github.lsj8367.domain.Result;

public class ConsoleOutput {

    private static final String BALL = "볼";
    private static final String STRIKE = "스트라이크";
    private static final String OUT = "아웃";

    public void inputMessage() {
        System.out.print("숫자를 입력해 주세요: ");
    }

    public void resultMessage(final Result result) {
        if (result.isOnlyBall()) {
            println(result.getBall() + BALL);
        }

        if (result.isOnlyStrike()) {
            println(result.getStrike() + STRIKE);
        }

        if (result.isOut()) {
            println(result.isOut() + OUT);
        }

        if (result.isStrikeAndBall()) {
            println(result.getStrike() + STRIKE + " " + result.getBall() + BALL);
        }
    }

    private void println(final String message) {
        System.out.println(message);
    }

    public void correctMessage() {
        println("정답입니다!!!");
    }

    public void errorMessage(final Exception e) {
        println(e.getMessage());
    }

}
