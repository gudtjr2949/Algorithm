import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr, arr2;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(bf.readLine());

        st = new StringTokenizer(bf.readLine());

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < M ; i++) {
            int key = Integer.parseInt(st.nextToken());

            sb.append(upperBound(key) - lowerBound(key)).append(" ");
        }

        System.out.println(sb);
    }

    // key 이상의 arr1[mid] 최초 인덱스을 리턴
    static int lowerBound(int key) {
        int left = 0;
        int right = N;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= key) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // key 를 초과하는 arr1[mid] 최초 인덱스을 리턴
    static int upperBound(int key) {
        int left = 0;
        int right = N;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] > key) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

}