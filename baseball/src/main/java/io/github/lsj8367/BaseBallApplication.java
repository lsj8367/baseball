package io.github.lsj8367;

import io.github.lsj8367.application.GameProcess;
import io.github.lsj8367.domain.Matcher;
import io.github.lsj8367.domain.Result;
import io.github.lsj8367.infrastructure.RandomNumberGenerateStrategy;
import io.github.lsj8367.interfaces.ConsoleInput;
import io.github.lsj8367.interfaces.ConsoleOutput;

public class BaseBallApplication {

    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();
        ConsoleOutput output = new ConsoleOutput();

        GameProcess process = GameProcess.run(new Matcher(), new RandomNumberGenerateStrategy());

        while (true) {
            output.inputMessage();
            try {
                String guessNumber = input.guessNumber();
                final Result result = process.guessNumber(guessNumber);
                if (result.isCorrect()) {
                    output.correctMessage();
                    break;
                }
                output.resultMessage(result);
            } catch (Exception e) {
                output.errorMessage(e);
            }
        }
    }

}
