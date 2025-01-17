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
        input();
        solve();
        printAnswer();
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (list.isEmpty() || list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
            } else {
                int idx = find(arr[i]);
                list.set(idx, arr[i]);
            }
        }
    }

    static int find(int key) {
        int left = 0;
        int right = list.size()-1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < key) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static void printAnswer() {
        System.out.println(list.size());
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        arr = new int[N];
        list = new ArrayList<>();
    }
}