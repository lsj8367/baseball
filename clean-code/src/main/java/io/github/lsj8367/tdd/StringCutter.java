package io.github.lsj8367.tdd;

public record StringCutter(String str) {

    private static final String CUT_TARGET = "A";
    private static final String BLANK = "";

    public String removeAFirstTwoCharacter() {

        if (str.length() < 2) {
            return str.replace("A", "");
        }

        final String front = str.substring(0, 2).replace(CUT_TARGET, BLANK);
        final String back = str.substring(2);
        return front + back;
    }
}
