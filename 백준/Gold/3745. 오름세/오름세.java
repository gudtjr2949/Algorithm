import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] P;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));) {
            String s = null;

            while ((s = bf.readLine()) != null) {
                N = Integer.parseInt(s.trim());
                P = new int[N];

                StringTokenizer st = new StringTokenizer(bf.readLine());

                for (int i = 0; i < N; i++) {
                    P[i] = Integer.parseInt(st.nextToken());
                }

                sb.append(solve()).append("\n");
            }
        } catch (Exception e) {

        } finally {
            System.out.println(sb);
        }
    }

    static int solve() {
        List<Integer> dp = new ArrayList<>();
        dp.add(Integer.MIN_VALUE);

        for (int i = 0; i < N; i++) {
            int num = P[i];
            int left = 0;
            int right = dp.size() - 1;

            if (num > dp.get(right)) {
                dp.add(num);
            } else {
                int idx = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (dp.get(mid) < num) {
                        left = mid + 1;
                    } else {
                        idx = mid;
                        right = mid - 1;
                    }
                }
                dp.set(idx, num);
            }
        }
        return dp.size() - 1;
    }
}