import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int D, N, idx, answer;
    static int[] oven, pizza;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        oven = new int[D];
        pizza = new int[N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < D ; i++) {
            oven[i] = Integer.parseInt(st.nextToken());
            if (i != 0 && oven[i] > oven[i-1]) oven[i] = oven[i-1];
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int pizzaIdx = 0;

        for (int i = D-1 ; i >= 0 ; i--) {
            if (pizza[pizzaIdx] <= oven[i]) pizzaIdx++;

            if (pizzaIdx == N) {
                answer = i+1;
                return;
            }
        }
    }
}