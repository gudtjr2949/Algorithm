import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            A = new int[Integer.parseInt(st.nextToken())];
            B = new int[Integer.parseInt(st.nextToken())];

            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < A.length; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < B.length; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);
            Arrays.sort(B);

            int sum = 0;

            for (int i = 0 ; i < A.length ; i++) {
                int left = lower(A[i]);
                sum += left;
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }

    // 찾고자 하는 값의 최초 위치
    static int lower(int num) {
        int left = 0;
        int right = B.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (B[mid] < num) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}