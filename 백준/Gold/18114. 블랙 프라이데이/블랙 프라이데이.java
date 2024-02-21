import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static boolean findAnswer = false;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        one();
        if (!findAnswer) two();
        if (!findAnswer) three();

        if (!findAnswer) System.out.println(0);
    }

    private static void one() {
        int left = 0;
        int right = N-1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] > C) {
                right = mid-1;
            } else if (arr[mid] == C) {
                System.out.println(1);
                findAnswer = true;
                return;
            } else {
                left = mid+1;
            }
        }
    }

    private static void two() {
        for (int i = 0 ; i < N-1 ; i++) { // 반드시 뽑는 첫번째 물건
            int left = i+1;
            int right = N-1;
            int sum = C - arr[i];
            while (left <= right) {
                int mid = (left + right) / 2;

                if (arr[mid] > sum) {
                    right = mid-1;
                } else if (arr[mid] == sum) {
                    System.out.println(1);
                    findAnswer = true;
                    return;
                } else {
                    left = mid+1;
                }
            }
        }
    }

    private static void three() {
        for (int i = 0 ; i < N-2 ; i++) { // 반드시 뽑는 첫번째 물건
            for (int j = i+1 ; j < N-1 ; j++) { // 반드시 뽑는 두번째 물건
                int left = j+1;
                int right = N-1;
                int sum = C - arr[i] - arr[j];
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (arr[mid] > sum) {
                        right = mid-1;
                    } else if (arr[mid] == sum) {
                        System.out.println(1);
                        findAnswer = true;
                        return;
                    } else {
                        left = mid+1;
                    }
                }
            }
        }
    }

}