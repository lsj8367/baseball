package io.github.lsj8367.infrastructure;

import io.github.lsj8367.domain.NumberGenerateStrategy;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class RandomNumberGenerateStrategy implements NumberGenerateStrategy {

    private final RandomGenerator randomGenerator = RandomGenerator.getDefault();

    @Override
    public String generate() {
        return randomGenerator.ints(0, 10)
            .distinct()
            .limit(3)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining());
    }

}
