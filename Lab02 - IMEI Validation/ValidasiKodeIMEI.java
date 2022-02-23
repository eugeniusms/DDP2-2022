import java.util.Scanner;

public class ValidasiKodeIMEI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int total = input.nextInt();
        String[] codeData =  new String[total];
        for (int i = 0; i < total; i++) {
            long temp = input.nextLong();
            // first check length and initial code 
            if (firstCheck(temp)) {
                codeData[i] = validityCheck(temp);
            } else {
                codeData[i] = "NO";
            }
        }

        // print array
        for (int i = 0; i < total; i++) {
            System.out.println(codeData[i]);
        }
        input.close();
    }

    public static boolean firstCheck(long code) {
        if (Math.floor(code / Math.pow(10,10)) < 10) { // check length
            if ((Math.floor(code / Math.pow(10,10)) == 2) 
                || (Math.floor(code / Math.pow(10,9)) == 18)) { // line wrapping
                return true;
            }
        }
        return false; // if condition 1 & 2 not completed
    }

    public static String validityCheck(long code) {
        long totalOdd = sumOddIndex(code);
        long totalEven = sumEvenIndex((long) code / 10);
        if ((totalOdd + totalEven) % 10 == 0) {
            return "YES";
        } else {
            return "NO";
        }
    }

    // sumOddIndex(21067504841)
    public static long sumOddIndex(long code) {
        if (code == 0) {
            return 0;
        } else {
            long lastDigit = code % 10;
            long newCode = (long) code / 100;
            System.out.println(lastDigit+" "+code);

            if (lastDigit*2 <= 9) { // if one digit
                return lastDigit*2 + sumOddIndex(newCode);
            } else {
                return 1 + (lastDigit*2 % 10) + sumOddIndex(newCode);
            }
        }
    }

    public static long sumEvenIndex(long code) {
        if (code == 0) {
            return 0;
        } else {
            long lastDigit = code % 10;
            long newCode = (long) code / 100; 
            System.out.println(lastDigit+" "+code);
            return lastDigit + sumEvenIndex(newCode);
        }
    }
}