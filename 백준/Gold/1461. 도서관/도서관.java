import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static List<Integer> negative, positive;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        negative = new ArrayList<>();
        positive = new ArrayList<>();

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        int max = 0;

        for (int i = 0 ; i < negative.size() ; i+=K) {
            answer += (negative.get(i) * -2);
            max = Math.max(negative.get(i) * -1, max);
        }

        for (int i = 0 ; i < positive.size() ; i+=K) {
            answer += (positive.get(i) * 2);
            max = Math.max(positive.get(i), max);
        }

        answer -= max;
    }
}