package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1124 : 언더프라임
public class BOJ_1124 {

    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        int A = Integer.parseInt(s_arr[0]);
        int B = Integer.parseInt(s_arr[1]);

        for (int i = A ; i <= B ; i++) {
            if (prime(i)){
                solve(i);
            }
        }

        System.out.println(answer);
    }

    // 소인수분해
    private static void solve(int n) {
        int cnt = 0;

        for (int i = 2 ; i <= n ; i++){
            if (n % i == 0){
                cnt++;
                n /= i;
                i = 1;
            }
        }

        if (!prime(cnt)) {
            answer++;
        }
    }

    // 소수판별
    private static boolean prime(int n) {
        for (int i = 2 ; i < n ; i++) {
            if (n % i == 0) {
                return true;
            }
        }

        return false;
    }
}
