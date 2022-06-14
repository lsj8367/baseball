package io.github.lsj8367.b.simple.design.business.text;

public class TextHelper {

    public String swapLastTwoCharacters(String str) {
        if (str.length() < 2) {
            return str;
        }

        final char back = str.charAt(str.length() - 1);
        final char front = str.charAt(str.length() - 2);

        final String front2Chars = str.substring(0, str.length() - 2);

        return front2Chars + back + front;
    }

    public String truncateAInFirst2Positions(String str) {
        return null;
    }

}
