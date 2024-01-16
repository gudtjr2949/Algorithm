import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, H;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[N/2]; // 석순
        int[] arr2 = new int[N/2]; // 종유석

        for (int i = 0 ; i < N ; i++) {
            int length = Integer.parseInt(bf.readLine());

            if (i % 2 == 0) {
                arr1[i/2] = length;
            } else {
                arr2[i/2] = length;
            }
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int min = Integer.MAX_VALUE;
        int minCnt = 0;

        for (int i = 1 ; i <= H ; i++) {
            // 파괴한 i 구간의 석순과 종유석 수
            int sum = binarySearch(0, N/2, i, arr1) + binarySearch(0, N/2, H-i+1, arr2);

            if (sum < min) {
                min = sum;
                minCnt = 1;
            } else if (sum == min) {
                minCnt++;
            }
        }

        System.out.println(min + " " + minCnt);
    }

    static int binarySearch(int down, int up, int high, int[] arr) {
        while (down < up) {
            int mid = (up + down) / 2;

            if (arr[mid] >= high) {
                up = mid;
            } else {
                down = mid + 1;
            }
        }

        return arr.length - up;
    }
}