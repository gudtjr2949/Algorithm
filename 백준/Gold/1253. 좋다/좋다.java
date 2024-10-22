import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken());


        Arrays.sort(arr);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            int left = 0;
            int right = N-1;

            while (left < right) {
                long sum = arr[left] + arr[right];

                if (sum > arr[i]) right--;
                else if (sum < arr[i]) left++;
                else {
                    if (left != i && right != i) {
                        answer++;
                        break;
                    }
                    else if (right == i) right--;
                    else left++;
                }
            }
        }
    }
}