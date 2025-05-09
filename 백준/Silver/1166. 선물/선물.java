import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, L, W, H;
    static double MIN, answer;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        double left = 0.0;
        double right = MIN;

        for(int i = 0 ; i < 5000 ; i++){
            double mid = (left + right) / 2;
            if (calBox(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        answer = left;
    }

    static boolean calBox(double mid) {
        return N <= (long)(L/mid)*(long)(W/mid)*(long)(H/mid);
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        MIN = Math.min(L, Math.min(W, H));
    }
}