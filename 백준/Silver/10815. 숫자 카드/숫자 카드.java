import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = 0;
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

        M = Integer.parseInt(bf.readLine());

        int[] answer = new int[M];

        st = new StringTokenizer(bf.readLine());
        
        for (int i = 0 ; i < M ; i++) {
            answer[i] = solve(Long.parseLong(st.nextToken()));
        }

        for (int i = 0 ; i < M ; i++) {;
            System.out.print(answer[i] + " ");
        }

    }

    // 입력받은 num이 arr에 있는지 확인해야 함
    static int solve(long num) {
        int left = 0;
        int right = N;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] > num) {
                right = mid;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                return 1;
            }
        }

        return 0;
    }
}