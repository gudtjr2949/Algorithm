import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr);
        if (arr[0] != 1) {
            answer = 1;
            return;
        }

        int last = 1;

        for (int i = 1 ; i < N ; i++) {
            if ((last + 1) < arr[i]) {
                answer = last + 1;
                break;
            }
            last += arr[i];
        }

        answer = last+1;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        arr = new int[N];
    }
}