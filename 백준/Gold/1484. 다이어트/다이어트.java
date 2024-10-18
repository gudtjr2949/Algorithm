import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static long G;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        G = Long.parseLong(bf.readLine());
        list = new ArrayList<>();
        solve();

        if (list.size() == 0)
            System.out.println(-1);
        else {
            Collections.sort(list);
            StringBuilder sb = new StringBuilder();

            for (Integer num : list)
                sb.append(num).append("\n");

            System.out.println(sb);
        }
    }

    static void solve() {
        int left = 2; // 현재 몸무게
        int right = 1; // 기억하는 몸무게

        while (left <= 100000) {
            long result = (left * left) - (right * right);

            if (result == G) {
                list.add(left);
            }

            if (result > G) right++;
            else left++;
        }
    }
}