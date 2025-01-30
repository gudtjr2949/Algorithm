import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, H;
    static int[] prefix;
    static int[][] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        findAnswer();
        System.out.println(sb);
    }

    static void findAnswer() {
        Arrays.sort(prefix);

        int min = prefix[0];
        int minCnt = 1;

        for (int i = 1 ; i <= H ; i++) {
            if (prefix[i] == min) minCnt++;
            else break;
        }

        sb.append(min).append(" ").append(minCnt);
    }


    static void solve() {
        Arrays.sort(arr[0]);
        Arrays.sort(arr[1]);

        for (int i = 1 ; i <= H ; i++) {
            prefix[i] = binarySearch(0, i) + binarySearch(1, H-i+1);
        }
    }

    static int binarySearch(int idx, int target) {
        int left = 0;
        int right = N/2;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[idx][mid] >= target) right = mid;
            else left = mid+1;
        }

        return (N / 2) - left;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            if (i % 2 == 0) {
                arr[0][i/2] = Integer.parseInt(bf.readLine());
            } else {
                arr[1][i/2] = Integer.parseInt(bf.readLine());
            }
        }
    }

    static void init() {
        arr = new int[2][N/2];
        prefix = new int[H+1];
        prefix[0] = N;
        sb = new StringBuilder();
    }
}