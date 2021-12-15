import java.util.*;
public class LongestCommonSubsequence {
    /**
     * Given two strings, find the longest common subsequence between the two strings
     */
    public static void main(String[] args) {
        correctnessTest();
    }

    public static void correctnessTest() {
        for(int i = 0; i < 30; i++) {
            String A = randomString(i);
            String B = randomString(i);
            int expected = LCSlow(A, B);
            int actual = LCSFast(A, B);
            if(actual != expected) {
                System.out.println(actual);
                System.out.println(expected);
                System.out.println("Test Failed for String of length " + i);
                break;
            }
            System.out.println("Test Passed for String  of length " + i);
        }
    }

    public static int LCSlow(String a, String b) {
        if (a.equals("") || b.equals("")) {
            return 0;
        } else if (a.charAt(0) != b.charAt(0)) {
            return Math.max(LCSlow(a.substring(1), b), LCSlow(a, b.substring(1)));
        }
        return 1 + LCSlow(a.substring(1), b.substring(1));
    }


    public static String LCSlowVerbose(String a, String b) {
        if (a.equals("") || b.equals("")) {
            return "";
        } else if (a.charAt(0) != b.charAt(0)) {
            String A = LCSlowVerbose(a.substring(1), b);
            String B = LCSlowVerbose(a, b.substring(1));
            return A.length() > B.length() ? A : B;
        }
        return a.charAt(0) + LCSlowVerbose(a.substring(1), b.substring(1));
    }

    public static int LCSFast(String a, String b) {
        int[][] res = new int[a.length() + 1][b.length() + 1];
        for(int i = 1; i < a.length() + 1; i++) {
            for(int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);
                } else {
                    res[i][j] = 1 + res[i - 1][j - 1];
                }
            }
        }
        return res[a.length()][b.length()];
    }

    public static String LCSFastVerbose(String a, String b) {
        String[][] res = new String[a.length() + 1][b.length() + 1];
        for(int i = 0; i < a.length() + 1; i++) {
            res[i][0] = "";
        }
        for(int j = 0; j < b.length() + 1; j++) {
            res[0][j] = "";
        }

        for(int i = 1; i < a.length() + 1; i++) {
            for(int j = 1; j < b.length() + 1; j++) {
                if(a.charAt(i - 1) != b.charAt(j - 1)) {
                    String A = res[i - 1][j];
                    String B = res[i][j - 1];
                    if(A.length() > B.length()) {
                        res[i][j] = A;
                    } else {
                        res[i][j] = B;
                    }
                }
                else {
                    res[i][j] = res[i - 1][j - 1] + a.charAt(i - 1);
                }
            }
        }
        return res[a.length()][b.length()];
    }


    public static String randomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
