import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(bf.readLine());

        st = new StringTokenizer(bf.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < M ; i++) {
            if (binarySearch(Integer.parseInt(st.nextToken()))) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }

    static boolean binarySearch(int M) {
        int left = 0;
        int right = N-1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] > M) {
                right = mid-1;
            } else if (arr[mid] == M){
                return true;
            } else {
                left = mid+1;
            }
        }

        return false;
    }
}