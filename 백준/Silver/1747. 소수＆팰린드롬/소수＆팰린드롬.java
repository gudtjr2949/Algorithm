import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        while (true) {
            if (checkPalind(N) && checkPrime(N)) {
                break;
            }

            N++;
        }

        if (N == 1) {
            N = 2;
        }

        System.out.println(N);
    }

    static boolean checkPrime(int num) {
        for (int i = 2 ; i <= Math.sqrt(num) ; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    static boolean checkPalind(int num) {
        String s = Integer.toString(num);

        int length = s.length() - 1;

        for (int i = 0 ; i <= length / 2 ; i++) {
            if (s.charAt(i) != s.charAt(length - i)) {
                return false;
            }
        }

        return true;
    }
}