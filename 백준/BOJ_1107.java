package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 1107 : 리모컨
public class BOJ_1107 {

    static int N;
    static boolean[] number;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        int M = Integer.parseInt(bf.readLine());
        number = new boolean[10];

        if (M > 0) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            for (int i = 0 ; i < 10 ; i++)
                number[i] = true;

            for (int i = 0 ; i < M ; i++)
                number[Integer.parseInt(s_arr[i])] = false;
        }

        up();

        down();

        if (answer == Integer.MAX_VALUE)
            answer = 0;

        System.out.println(answer);
    }

    private static void up() {
        int tmp = N;

        for (int i = 0 ; i <= Math.abs(100 - N) ; i++) {
            if (check(tmp + i)) { // true 리턴 시, 해당 숫자 리모콘으로 입력 가능
                tmp = tmp + i;

                String tmp_str = "";
                tmp_str += tmp;

                int cnt = tmp_str.length() + (tmp - N);

                answer = Math.min(answer, cnt);

                return;
            }
        }

        answer = Math.min(answer, Math.abs(100 - N));
    }

    private static void down() {
        int tmp = N;

        for (int i = 0 ; i <= Math.abs(100 - N) ; i++) {
            if (tmp - i >= 0) {
                if (check(tmp - i)) {
                    tmp = tmp - i;

                    String tmp_str = "";

                    tmp_str += tmp;

                    int cnt = tmp_str.length() + (N - tmp);

                    answer = Math.min(answer, cnt);

                    return;
                }
            }
        }

        answer = Math.min(answer, Math.abs(N - 100));
    }

    private static boolean check(int tmp) {
        String s = "";
        s += tmp;

        for (int i = 0 ; i < s.length() ; i++) {
            if (!number[s.charAt(i) - '0']) {
                return false;
            }
        }

        return true;

    }
}
