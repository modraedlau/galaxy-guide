package galaxy.guide.utils;

import java.util.List;

/**
 * @author Modraed Lau
 */
public class StringUtils {

    private static final char L_A = 'a';
    private static final char L_Z = 'z';

    private static final char U_A = 'A';
    private static final char U_Z = 'Z';

    public static boolean isInteger(String str) {
        final String number = "0123456789";
        for (int i = 0; i < str.length(); i++) {
            if (number.indexOf(str.charAt(i)) == -1) {
                return false;
            }
            if (i == 0 && str.charAt(0) == '0') {
                return false;
            }
        }
        return true;
    }

    public static boolean isFirstLowercase(String str) {
        return isFirstBetween(str, L_A, L_Z);
    }

    public static boolean isFirstUppercase(String str) {
        return isFirstBetween(str, U_A, U_Z);
    }

    private static boolean isFirstBetween(String str, char min, char max) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char first = str.charAt(0);
        return first >= min && first <= max;
    }
}
