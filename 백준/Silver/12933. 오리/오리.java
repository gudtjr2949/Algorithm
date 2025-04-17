import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, answer;
    static char[] arr, QUACK = {'q', 'u', 'a', 'c', 'k'};

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(solve());
    }

    static int solve() {
        if (N % 5 != 0) {
            return -1;
        }

        int remain = N;
        int cnt = 0;

        while (remain != 0) {
            int pt = 0;
            int idx = 0;

            boolean chk = false;

            int[] tmp = new int[5];

            // 오리 한마리가 울 수 있는 최대 횟수 찾기
            while (idx < N) {
                if (arr[idx] == QUACK[pt]) {
                    tmp[pt++] = idx;

                    if (pt == 5) {
                        chk = true;
                        remain -= 5;
                        pt = 0;
                        for (int i = 0; i < 5; i++) arr[tmp[i]] = '\0';
                    }
                }

                idx++;
            }

            if (chk) cnt++;
            else break;
        }

        return remain == 0 ? cnt : -1;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        N = s.length();
        arr = new char[N];
        for (int i = 0 ; i < N ; i++) {
            arr[i] = s.charAt(i);
        }
    }
}