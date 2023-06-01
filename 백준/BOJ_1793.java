package coding_test.백준;

import java.math.BigInteger;
import java.util.Scanner;


// 백준 1793 : 타일링
public class BOJ_1793 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            int N = Integer.parseInt(sc.nextLine());

            BigInteger[] memo = new BigInteger[N+1];
            if (N == 0) {
                memo[0] = new BigInteger("1");
            }
            else if (N == 1) {
                memo[1] = new BigInteger("1");
            }
            else if (N > 1) {
                memo[1] = new BigInteger("1");
                memo[2] = new BigInteger("3");

                for (int i = 3; i <= N; i++) {
                    BigInteger num1 = memo[i - 1];
                    BigInteger num2 = memo[i - 2].multiply(new BigInteger("2"));
                    memo[i] = num1.add(num2);
                }
            }

            System.out.println(memo[N]);
        }
    }
}
