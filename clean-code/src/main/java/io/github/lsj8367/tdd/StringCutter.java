package io.github.lsj8367.tdd;

public class StringCutter {

    private static final String CUT_TARGET = "A";
    private static final String BLANK = "";

    public String removeAFirstTwoCharacter(final String str) {

        if (str.length() < 2) {
            return str.replace("A", "");
        }

        final String front = str.substring(0, 2).replace(CUT_TARGET, BLANK);
        final String back = str.substring(2);
        return front + back;
    }
}
