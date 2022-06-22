package io.github.lsj8367.refactoring.movie;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.lsj8367.refactoring.movie.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MovieTest {

    @Test
    @DisplayName("null이면 false")
    void isRatingNullTest() {
        assertRatingFalse(null);
    }

    @ParameterizedTest
    @DisplayName("등급이 B 인경우 4까지만 등급 매기기 가능")
    @ValueSource(strings = {"B1", "B2", "B3", "B4"})
    void testIsValidRating(final String rating) {
        assertRatingTrue(rating);
    }

    @ParameterizedTest
    @DisplayName("등급이 B 인경우 5이상이면 false")
    @ValueSource(strings = {"B5", "B10"})
    void isValidRatingBSizeGreaterThan1(final String rating) {
        assertRatingFalse(rating);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A01", "A10", "A11", "A99"})
    @DisplayName("등급이 A 인경우 뒤에 2자리만 가능")
    void isRatingA(final String rating) {
        assertRatingTrue(rating);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "A2", "A100", "A786"})
    @DisplayName("등급이 A 지만 숫자가 두자리가 아닌 경우")
    void isRatingAAndSizeNot2(final String rating) {
        assertRatingFalse(rating);
    }

    private void assertRatingTrue(final String rating) {
        assertThat(new Movie(rating).isValidRating()).isTrue();
    }

    private void assertRatingFalse(final String rating) {
        assertThat(new Movie(rating).isValidRating()).isFalse();
    }

}