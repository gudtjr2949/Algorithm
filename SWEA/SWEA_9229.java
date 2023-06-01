package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 9229 : 한빈이와 Spot Mart
public class SWEA_9229 {

    static int N;
    static int M;
    static int[] A;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++) {
            String nm = bf.readLine();
            String[] nm_arr = nm.split(" ");

            N = Integer.parseInt(nm_arr[0]);
            M = Integer.parseInt(nm_arr[1]);
            A = new int[N];
            answer = -1;

            String s = bf.readLine();
            String[] s_arr = s.split(" ");
            for (int j = 0 ; j < N ; j++) {
                A[j] = Integer.parseInt(s_arr[j]);
            }

            solve(new int[2], new boolean[N], 0);

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void solve(int[] input, boolean[] visited, int idx) {
        if (idx == 2) {
            int sum = input[0] + input[1];

            if (sum <= M) {
                answer = Math.max(answer, sum);
            }

            return;
        }

        for (int i = 0 ; i < N ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = A[i];
                solve(input, visited, idx + 1);
                visited[i] = false;
            }
        }
    }
}
