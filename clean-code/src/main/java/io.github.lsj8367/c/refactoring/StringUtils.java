package io.github.lsj8367.c.refactoring;

public class StringUtils {

    public static boolean isNumeric(String substring) {
        try {
            Integer.parseInt(substring);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}