import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][2];
        list = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[i][0] = A;
            arr[i][1] = B;
        }

        solve();

        System.out.println(N-list.size());
    }

    static void solve() {
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0 ; i < N ; i++) {
            if (list.size() == 0 || list.get(list.size()-1) < arr[i][1]) {
                list.add(arr[i][1]);
            } else {
                int idx = binarySearch(arr[i][1]);
                list.set(idx, arr[i][1]);
            }
        }
    }

    static int binarySearch(int num) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < num) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}