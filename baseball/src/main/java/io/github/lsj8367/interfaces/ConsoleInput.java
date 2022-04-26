package io.github.lsj8367.interfaces;

import io.github.lsj8367.util.NumberValidator;
import java.util.Scanner;

public class ConsoleInput {

    private final Scanner sc = new Scanner(System.in);

    public String guessNumber() {
        return NumberValidator.validation(sc.nextLine());
    }

}
