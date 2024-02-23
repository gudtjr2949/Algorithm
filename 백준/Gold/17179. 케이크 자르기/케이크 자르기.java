import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, L, Q;
    static int[] arr, length;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[M];

        length = new int[M+1];

        for (int i = 0 ; i < M ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        length[0] = arr[0];

        for (int i = 1 ; i < M ; i++) {
            length[i] = arr[i] - arr[i-1];
        }

        length[M] = L - arr[M-1];

        for (int i = 0 ; i < N ; i++) {
            Q = Integer.parseInt(bf.readLine());
            sb.append(binarySearch()).append("\n");
        }

        System.out.println(sb);
    }

    static int binarySearch() {
        int left = 0;
        int right = L;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (solve(mid) > Q) { // 자르는 횟수가 너무 적음 -> 더 잘라야 함 -> mid 를 더 작게 해야 함
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    // 최소 롤케익 길이가 mid 일 때, 자르는 횟수
    static int solve(int mid) {
        int nowLength = 0;
        int cnt = 0;

        for (int i = 0 ; i <= M ; i++) {
            if (nowLength + length[i] < mid) {
                nowLength += length[i];
            } else {
                cnt++;
                nowLength = 0;
            }
        }

        return cnt;
    }
}