package io.github.lsj8367;

public class Main {

    public static void main(String[] args) {
        int count = 0;
        Input input = new Input();
        BaseBallNumbers baseBallNumbers = new BaseBallNumbers();
        baseBallNumbers.generate();

        String answer = baseBallNumbers.createNumber();

        while (count < 5) {
            MatchGenerator matchGenerator = new MatchGenerator(answer, input.guessNumber());
            input.matchInfo(matchGenerator.strike(), matchGenerator.ball());
            count++;
        }

    }

}
