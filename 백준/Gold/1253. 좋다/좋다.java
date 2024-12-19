import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new long[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        solve();


        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            int left = 0;
            int right = N-1;

            long key = arr[i];

            while (left < right) {
                long sum = arr[left] + arr[right];

                if (sum == key) {
                    if (left == i) left++;
                    else if (right == i) right--;
                    else {
                        answer++;
                        break;
                    }
                } else if (sum < key) {
                    left++;
                } else {
                    right--;
                }
            }
        }

    }
}