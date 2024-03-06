import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int[] arr;
    static StringBuilder[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(bf.readLine());

        arr = new int[(int) Math.pow(2, K)-1];
        answer = new StringBuilder[K];
        for (int i = 0 ; i < K ; i++) {
            answer[i] = new StringBuilder();
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, arr.length-1, 0);

        for (int i = 0 ; i < K ; i++) {
            System.out.println(answer[i]);
        }
    }

    static void solve(int left, int right, int floor) {
        if (floor == K) return;

        int mid = (left + right) / 2;
        answer[floor].append(arr[mid]).append(" ");

        // 왼쪽 탐색
        solve(left, mid-1, floor+1);
        solve(mid+1, right, floor+1);
    }
}