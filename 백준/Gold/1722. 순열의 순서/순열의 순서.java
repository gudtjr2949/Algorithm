import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, problem;
    static long K;
    static int[] arr;
    static long[] factorials;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        visited = new boolean[21];
        factorials = new long[21];
        arr = new int[N];

        factorials[0] = 1;
        for (int i = 1 ; i <= N ; i++) {
            factorials[i] = factorials[i-1] * i;
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        problem = Integer.parseInt(st.nextToken());
        if (problem == 1) {
            K = Long.parseLong(st.nextToken());
            solve1();
        } else {
            for (int i = 0 ; i < N ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            solve2();
        }
    }

    static void solve1() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 1 ; j <= N ; j++) {
                if (visited[j]) continue;

                if (factorials[N-i-1] < K) {
                    K -= factorials[N-i-1];
                } else {
                    arr[i] = j;
                    visited[j] = true;
                    break;
                }
            }
        }

        for (int i = 0 ; i < N ; i++) System.out.print(arr[i] + " ");
    }

    static void solve2() {
        long answer = 1;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 1 ; j < arr[i] ; j++) {
                if (visited[j]) continue;
                answer += factorials[N-i-1];
            }

            visited[arr[i]] = true;
        }

        System.out.println(answer);
    }
}