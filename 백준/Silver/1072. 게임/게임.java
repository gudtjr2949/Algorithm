import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long X, Y, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        answer = -1;
        
        solve();


        System.out.println(answer);
    }

    static void solve() {
        long left = 0;
        long right = 1_000_000_000;

        long originPercent = getPercent(X, Y);

        while (left <= right) {
            long mid = (left + right) / 2;

            long winPercent = getPercent(X+mid, Y+mid);

            if (winPercent != originPercent) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
    }

    static long getPercent(long x, long y) {
        return (long) ((long) y * 100 / x);
    }
}