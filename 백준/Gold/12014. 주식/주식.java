import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] arr;
    static List<Integer> list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int idx = 0;
        while (idx++ < T) {
            input(bf);
            solve();
            findAnswer(idx);
        }
        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (list.isEmpty() || list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
                continue;
            }

            int idx = bs(arr[i]);
            list.set(idx, arr[i]);
        }
    }

    static int bs(int find) {
        int left = 0;
        int right = list.size()-1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < find) left = mid+1;
            else right = mid;
        }

        return left;
    }


    static void findAnswer(int idx) {
        sb.append("Case #").append(idx).append("\n");
        if (list.size() >= K) {
            sb.append(1).append("\n");
        } else {
            sb.append(0).append("\n");
        }
    }

    static void input(BufferedReader bf) throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        arr = new int[N];
        list = new ArrayList<>();
    }
}