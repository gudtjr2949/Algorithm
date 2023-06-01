package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 16435 : 스네이크버드
public class BOJ_16435 {

    static int N;
    static int L;
    static int[] h;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String nl = bf.readLine();
        String[] nl_arr = nl.split(" ");

        N = Integer.parseInt(nl_arr[0]);
        L = Integer.parseInt(nl_arr[1]);

        String s = bf.readLine();
        String[] s_arr = s.split(" ");
        h = new int[N];

        for (int i = 0 ; i < N ; i++) {
            h[i] = Integer.parseInt(s_arr[i]);
        }

        Arrays.sort(h);

        eat();

        System.out.println(L);
    }

    private static void eat() {
        for (int i = 0 ; i < N ; i++) {
            if (L >= h[i]) {
                L++;
            }
            else {
                return;
            }
        }
    }
}
