import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long answer;
    static int[] arr, dis;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        int minPrice = 1_000_000_001;
        for (int i = 0 ; i < N-1 ; i++) {
            minPrice = Math.min(minPrice, arr[i]);
            answer += minPrice * dis[i];
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N-1 ; i++) {
            dis[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        dis = new int[N-1];
        arr = new int[N];
    }
}