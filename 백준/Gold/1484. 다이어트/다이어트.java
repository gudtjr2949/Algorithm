import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int G;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        if (list.size() == 0) System.out.println(-1);
        else {
            for (Integer num : list) {
                System.out.println(num);
            }
        }
    }

    static void solve() {
        int left = 1;
        int right = 2;

        while (right < 100_000) {
            int diff = calWeight(right, left);

            if (diff <= G) {
                if (diff == G) {
                    list.add(right);
                }
                right++;
            } else {
                left++;
            }
        }
    }

    static int calWeight(int now, int before) {
        return (int) (Math.pow(now, 2) - Math.pow(before, 2));
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(bf.readLine());
    }
}