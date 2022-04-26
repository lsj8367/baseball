package io.github.lsj8367.domain;

public class Matcher {

    public Result checkNumber(final BaseBall generateNumber, final BaseBall inputNumber) {
        //여기서 볼, 스트라이크 정보 리턴
        int strikeCount = 0;
        int ballCount = 0;

        for (int i = 0; i < generateNumber.number().length(); i++) {
            strikeCount += isStrikeThenPlus1(i, generateNumber, inputNumber);
            ballCount += isBallThenPlus1(i, generateNumber, inputNumber);
        }

        return new Result(strikeCount, ballCount);
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
