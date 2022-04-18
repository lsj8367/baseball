package io.github.lsj8367.domain;

import io.github.lsj8367.NumberGenerateStrategy;

public record BaseBall(String number) {
    public static BaseBall create(final NumberGenerateStrategy strategy) {
        return new BaseBall(strategy.generate());
    }

}
