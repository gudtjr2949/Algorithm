import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N, S, answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());

        arr = new int[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0, 0);

        if (S == 0) { // S가 0일 때, dfs를 모두 돌렸을 때, 모든 arr의 수를 다 더하지 않은 경우 sum 가 0 이 되므로 하나 뺴줘야 함
            answer--;
        }

        System.out.println(answer);
    }

    static void dfs(int idx, int sum) {
        if (idx == N) {
            if (sum == S) {
                answer++;
            }
            return;
        }


        dfs(idx + 1, sum + arr[idx]);
        dfs(idx + 1, sum);
    }
}