import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long answer;
    static long[] A, B, C, D, arr1, arr2;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        A = new long[N];
        B = new long[N];
        C = new long[N];
        D = new long[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        arr1 = new long[N*N];
        arr2 = new long[N*N];

        int idx = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                arr1[idx] = A[i] + B[j];
                arr2[idx++] = C[i] + D[j];
            }
        }
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = N*N-1;

        while (left < N*N && right >= 0) {
            long sum = arr1[left] + arr2[right];

            if (sum == 0) {
                long lCnt = 0;
                long rCnt = 0;
                long preL = arr1[left];
                long preR = arr2[right];

                while (left < N*N && arr1[left] == preL) {
                    left++;
                    lCnt++;
                }

                while (right >= 0 && arr2[right] == preR) {
                    right--;
                    rCnt++;
                }

                answer += (rCnt * lCnt);
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
    }
}