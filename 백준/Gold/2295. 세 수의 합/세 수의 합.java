import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] arr, sumArr;
    static long answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new int[N];

        sumArr = new int[N*N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        int idx = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                sumArr[idx++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(arr);
        Arrays.sort(sumArr);

        for (int i = N-1 ; i >= 0 ; i--) {
            for (int j = 0 ; j < i ; j++) {
                int gap = arr[i] - arr[j];

                // gap 이 sum 배열 안에 있는지 검사
                if (binarySearch(gap)) {
                    answer = Math.max(answer, arr[i]);
                }
            }

        }


        System.out.println(answer);
    }

    // sum 에 있는 3개의 수를 합해서 num 을 만들수 있는지 판별하는 메서드
    static boolean binarySearch(int num) {
        int left = 0;
        int right = sumArr.length-1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (sumArr[mid] < num) { // sum 이 num 보다 작음 -> sum 이 더 커져야 함 -> left 가 더 커져야 함
                left = mid + 1;
            } else if (sumArr[mid] > num){
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}