import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, MAX;
    static boolean[] visited;
    static int[] arr = {1, 5, 10, 50};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        MAX = 50 * N + 1;
        visited = new boolean[MAX];
        combi(0, 0, 0);

        int answer = 0;

        for (int i = 0 ; i < MAX ; i++) 
            if (visited[i]) answer++;


        System.out.println(answer);
    }

    static void combi(int idx, int cur, int sum) {
        if (idx == N) {
            visited[sum] = true;
            return;
        }

        for (int i = cur ; i < 4 ; i++) {
            combi(idx+1, i, sum + arr[i]); // 다음 cur 을 i+1이 아닌 i를 하는 이유는, 중복을 허용한 조합이기 때문임
        }
    }
}