package coding_test.백준;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 11053 : 가장 긴 증가하는 부분 수열
public class BOJ_11053 {

    static int N, answer;
    static int[] arr;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new int[N];

        dist = new int[N];

        answer = 0;

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dist, 1);

        solve();

        System.out.println(answer);
    }

    private static void solve() {

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < i ; j++) {
                if (arr[i] > arr[j]) {
                    dist[i] = Math.max(dist[i], dist[j]+1);
                }
            }
        }

        for (int i = 0 ; i < N ; i++) {
            answer = Math.max(answer, dist[i]);
        }

    }
}
