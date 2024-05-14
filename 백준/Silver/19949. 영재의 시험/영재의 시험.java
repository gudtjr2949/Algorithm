import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[10];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < 10 ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(new int[10], 0);

        System.out.println(answer);
    }

    static void dfs(int[] input, int idx) {
        if (idx == 10) {
            if (countAnswer(input)) answer++;
            return;
        }

        for (int i = 1 ; i <= 5 ; i++) {
            if (idx < 2 || (idx >= 2 && (input[idx-2] != i || input[idx-1] != i))) {
                input[idx] = i;
                dfs(input, idx+1);
            }
        }
    }

    static boolean countAnswer(int[] input) {
        int cnt = 0;

        for (int i = 0 ; i < 10 ; i++) {
            if (input[i] == arr[i]) cnt++;
        }

        return cnt >= 5;
    }
}