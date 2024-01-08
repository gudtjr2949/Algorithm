import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());


        double left = 0;
        double right = Math.max(L, Math.max(W, H));
        double mid = 0;
        double answer = 0;

        for (int i = 0 ; i < 100 ; i++) {
            mid = (left + right) / 2;

            if ((long)(L / mid) * (long)(W / mid) * (long)(H / mid) < N) {
                right = mid;
            } else {
                answer = mid;
                left = mid;
            }
        }

        System.out.println(answer);
    }
}