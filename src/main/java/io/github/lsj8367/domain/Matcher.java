package io.github.lsj8367.domain;

public class Matcher {

    private static final int NONE_VALUE = 0;

    public Result checkNumber(final BaseBall generateNumber, final BaseBall inputNumber) {
        //여기서 볼, 스트라이크 정보 리턴
        int strikeCount = 0;
        int ballCount = 0;
        if (!generateNumber.number().contains(String.valueOf(inputNumber.number().charAt(0)))
            && !generateNumber.number().contains(String.valueOf(inputNumber.number().charAt(1)))
            && !generateNumber.number().contains(String.valueOf(inputNumber.number().charAt(2)))) {
            //아웃인경우
            return new Result(NONE_VALUE, NONE_VALUE, 3);
        }

        for (int i = 0; i < generateNumber.number().length(); i++) {
            strikeCount += isStrikeThenPlus1(i, generateNumber, inputNumber);
            ballCount += isBallThenPlus1(i, generateNumber, inputNumber);
        }

        return new Result(strikeCount, ballCount, NONE_VALUE);
    }


    private int isStrikeThenPlus1(final int index, final BaseBall generateNumber, final BaseBall inputNumber) {
        if (generateNumber.number().charAt(index) == inputNumber.number().charAt(index)) {
            return 1;
        }
        return 0;
    }

    private int isBallThenPlus1(final int index, final BaseBall generateNumber, final BaseBall inputNumber) {

        if (generateNumber.number().charAt(index) != inputNumber.number().charAt(index)
            && generateNumber.number().contains(String.valueOf(inputNumber.number().charAt(index)))) {
            return 1;
        }

        return 0;
    }

}
