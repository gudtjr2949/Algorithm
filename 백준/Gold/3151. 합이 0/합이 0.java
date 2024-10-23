import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum > 0) right--;
                else {
                    if (sum == 0) {

                        if (arr[left] == arr[right]) {
                            answer += ((right - left) * (right - left + 1)) / 2;
                            break;
                        } else {
                            int leftCnt = 1;
                            int rightCnt = 1;

                            while (arr[left] == arr[left + 1]) {
                                leftCnt++;
                                left++;
                            }

                            while (arr[right] == arr[right - 1]) {
                                rightCnt++;
                                right--;
                            }

                            answer += leftCnt * rightCnt;
                        }
                    }

                    left++;
                }
            }
        }
    }
}