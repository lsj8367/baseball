package io.github.lsj8367.domain;

public record BaseBall(String number) {
    public static BaseBall create(final NumberGenerateStrategy strategy) {
        return new BaseBall(strategy.generate());
    }

}
