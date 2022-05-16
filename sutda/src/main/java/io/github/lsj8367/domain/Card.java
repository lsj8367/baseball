package io.github.lsj8367.domain;

import java.util.Objects;

public class Card {

    private final int month;
    private final boolean light;

    public Card(final int month, final boolean light) {
        isNormalCard(month, light);
        this.month = month;
        this.light = light;
    }

    private void isNormalCard(final int month, final boolean light) {
        if (light) {
            isKwangCheck(month);
        }
    }

    private void isKwangCheck(final int month) {
        if (!(month == 1 || month == 3 || month == 8)) {
            throw new IllegalStateException("광은 1,3,8만 가능합니다.");
        }
    }

    public boolean isKwang() {
        return light;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return month == card.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month);
    }

}
