import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr);

        for (int i = 0 ; i < N-2 ; i++) {
            int left = i+1;
            int right = N-1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == 0) {
                    if (arr[left] == arr[right]) {
                        int distance = right - left + 1;
                        long cnt = (distance * (distance-1)) / 2;
                        answer += cnt;
                        break;
                    }

                    int origin = arr[left];
                    int leftCnt = 0;
                    while (arr[left] == origin) {
                        leftCnt++;
                        left++;
                    }

                    origin = arr[right];
                    int rightCnt = 0;
                    while (arr[right] == origin) {
                        rightCnt++;
                        right--;
                    }

                    answer += (leftCnt * rightCnt);
                } else {
                    if (sum < 0) left++;
                    else right--;
                }
            }
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) arr[i] = Integer.parseInt(st.nextToken());
    }

    static void init() {
        arr = new int[N];
    }
}