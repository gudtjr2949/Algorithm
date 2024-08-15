import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(bf.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        M = Integer.parseInt(bf.readLine());

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            int findNum = Integer.parseInt(st.nextToken());

            sb.append(upper(findNum) - lower(findNum)).append(" ");
        }

        System.out.println(sb);
    }

    static int lower(int findNum) {
        int left = 0;
        int right = N;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= findNum)
                right = mid;
            else
                left = mid+1;
        }

        return right;
    }

    static int upper(int findNum) {
        int left = 0;
        int right = N;

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] > findNum)
                right = mid;
            else
                left = mid+1;
        }

        return right;
    }
}