import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, location;
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
                location[i] = list.size()-1;
                continue;
            }

            int idx = bs(arr[i]);
            location[i] = idx;
            list.set(idx, arr[i]);
        }
    }

    // 이분탐색
    static int bs(int find) {
        int left = 0;
        int right = list.size()-1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < find) {
                left = mid+1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static void printAnswer() {
        System.out.println(list.size());
        int idx = list.size()-1;
        int[] answer = new int[list.size()];
        for (int i = N-1 ; i >= 0 ; i--) {
            if (location[i] == idx) {
                answer[idx] = arr[i];
                idx--;
            }
        }

        for (int num : answer) System.out.print(num + " ");
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
        location = new int[N];
        list = new ArrayList<>();
    }
}