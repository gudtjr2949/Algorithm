import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();
        
        System.out.println(list.size());
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (list.size() == 0 || list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
            } else {
                int idx = binarySearch(arr[i]);

                list.set(idx, arr[i]);
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