import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, dp;
    static List<Integer> list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(list.size());
        System.out.println(sb);
    }

    static void solve() {
        list = new ArrayList<>();

        int idx = 0;

        for (int i = 0 ; i < N ; i++) {
            if (list.size() == 0 || list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
                dp[i] = idx++;
            } else {
                int findIdx = findIdx(arr[i]);
                list.set(findIdx, arr[i]);
                dp[i] = findIdx;
            }
        }

        idx = list.size()-1;
        int[] result = new int[list.size()];

        for (int i = N-1 ; i >= 0 ; i--) {
            if (dp[i] == idx) {
                result[idx--] = arr[i];
            }
        }

        for (int i = 0 ; i < list.size() ; i++)
            sb.append(result[i]).append(" ");
    }

    static int findIdx(int target) {
        int left = 0;
        int right = list.size()-1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}