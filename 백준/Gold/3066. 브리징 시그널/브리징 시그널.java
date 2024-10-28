import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            arr = new int[N];
            list = new ArrayList<>();
            for (int i = 0 ; i < N ; i++)
                arr[i] = Integer.parseInt(bf.readLine());

            solve();

            sb.append(list.size()).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (list.size() == 0 || list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
            } else {
                int idx = findIdx(arr[i]);
                list.set(idx, arr[i]);
            }
        }
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

        return left;
    }
}