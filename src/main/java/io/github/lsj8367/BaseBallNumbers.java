package io.github.lsj8367;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BaseBallNumbers {

    private static final int MIN_VALUE = 1;
    private static final int MAX_RANGE = 9;

    private final Random random = new Random();
    private final Set<Integer> ballNumbers = new HashSet<>();

    public void generate() {
        while (ballNumbers.size() < 3) {
            ballNumbers.add(MIN_VALUE + random.nextInt(MAX_RANGE));
        }
    }

    public String createNumber() {
        final StringBuilder sb = new StringBuilder();
        ballNumbers.forEach(sb::append);
        return sb.toString();
    }

    public int getSize() {
        return ballNumbers.size();
    }

}
