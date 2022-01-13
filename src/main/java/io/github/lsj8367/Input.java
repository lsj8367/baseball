package io.github.lsj8367;

import java.util.Scanner;

public class Input {
    private final Scanner sc = new Scanner(System.in);

    public String guessNumber() {
        System.out.println("맞출 숫자를 입력하세요");
        return sc.next();
    }

    public void matchInfo(final int strike, final int ball) {
        if (strike == 3) {
            System.out.println("정답");
        }

        if (strike == 0 && ball == 0) {
            System.out.println("3 out");
        }
        System.out.println(strike + " strike" + " " + ball + " ball");
    }
}
