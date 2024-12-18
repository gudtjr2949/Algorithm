import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static String answer;
    static int[] P;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        P = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(bf.readLine());

        answer = "";

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int min = 50;
        int minIdx = 0;

        for (int i = 0 ; i < N ; i++) {
            if (P[i] <= min) {
                min = P[i];
                minIdx = i;
            }
        }

        arr = new char[51];
        int idx = 0;
        while (M >= min) {
            arr[idx++] = (char) (minIdx + '0');
            M -= min;
        }

        int start = 0;

        for (int i = 0 ; i < idx ; i++) {
            for (int j = N-1 ; j >= 0 ; j--) {
                if (P[j] <= M + min) {
                    M += min - P[j];
                    arr[i] = (char) (j + '0');
                    break;
                }
            }

            if (arr[start] == '0') {
                start++;
                M += min;
            }
        }

        if (start == idx) answer = "0";
        else {
            for (int i = start ; i < idx ; i++) {
                answer += arr[i];
            }
        }
    }
}