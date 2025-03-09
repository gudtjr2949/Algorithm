import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr, locations;
    static List<Integer> list, answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        findAnswer();
        System.out.println(answer.size());
        System.out.println(sb);
    }

    static void findAnswer() {
        int idx = list.size()-1;

        for (int i = N-1 ; i >= 0 ; i--) {
            if (locations[i] == idx) {
                answer.add(arr[i]);
                idx--;
            }
        }

        for (int i = answer.size()-1 ; i >= 0 ; i--) {
            sb.append(answer.get(i)).append(" ");
        }
    }

    static void solve() {
        for (int i = 0 ; i < N ; i++) {
            if (list.isEmpty() || list.get(list.size()-1) < arr[i]) {
                list.add(arr[i]);
                locations[i] = list.size()-1;
                continue;
            }

            int idx = bs(arr[i]);
            locations[i] = idx;
            list.set(idx, arr[i]);
        }
    }

    static int bs(int num) {
        int left = 0;
        int right = list.size()-1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) >= num) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return left;
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
        locations = new int[N];
        list = new ArrayList<>();
        answer = new ArrayList<>();
    }
}