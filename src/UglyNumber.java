public class UglyNumber {
    /**
     * Problem Statement:
     * Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence
     * 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15,… shows the first 11 ugly numbers.
     * By convention, 1 is included. Given a number n, the task is to find n’th Ugly number.
     */
    public static void main(String[] args) {
        System.out.println(uglyNumberSlow(150));
    }

    public static int uglyNumberSlow(int n) {
        int i = 1;
        int curr = 0;
        while(n != 0) {
            if (isUgly(i)) {
                curr = i;
                n--;
            }
            i++;
        }
        return curr;
    }

    public static boolean isUgly(int n) {
        while (n % 5 == 0 || n % 3 == 0 || n % 2 == 0) {
            if (n % 5 == 0) {
                n = n / 5;
            } else if (n % 3 == 0) {
                n = n / 3;
            } else {
                n = n / 2;
            }
        }
        return n == 1;
    }
}
