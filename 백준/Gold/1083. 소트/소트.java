import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static List<Integer> list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {
        int left = 0;
        int right = S;
        if (right >= N) right = N-1;

        while (S > 0 && left < right && left < N && right < N) {
            int maxIdx = findMaxIdx(left, right);
            if (left == maxIdx) {
                left++;
                right++;
                if (right >= N) right = N-1;
                continue;
            }

            int max = list.remove(maxIdx);
            list.add(left, max);
            S -= maxIdx - left;
            left++;
            right = left + S;
            if (right >= N) right = N-1;
        }

        for (int num : list) {
            sb.append(num).append(" ");
        }
    }

    static int findMaxIdx(int left, int right) {
        int max = list.get(left);
        int maxIdx = left;

        for (int i = left ; i <= right ; i++) {
            if (max < list.get(i)) {
                max = list.get(i);
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        S = Integer.parseInt(bf.readLine());
    }

    static void init() {
        list = new ArrayList<>();
    }
}