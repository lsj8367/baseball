package io.github.lsj8367.refactoring.movie;

import io.github.lsj8367.refactoring.StringUtils;
import java.util.List;
import java.util.Objects;

public record Movie(String rating) {

    private static final List<String> RATING_B_LIST = List.of(
        "B1", "B2", "B3", "B4"
    );

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {
        if (Objects.isNull(rating)) {
            return false;
        }

        if (isRatingB()) {
            return true;
        }

        return isRatingA();
    }

    private boolean isRatingB() {
        return RATING_B_LIST.contains(rating);
    }

    private boolean isRatingA() {
        char frontChar = rating.charAt(0);
        return frontChar == 'A'
            && rating.length() == 3
            && StringUtils.isNumeric(rating.substring(1, 3));
    }

}
