import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static Report[] reports;
    static class Report {
        int d, t;
        public Report(int d, int t) {
            this.d = d;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(reports, (o1, o2) -> o2.t - o1.t);

        int recentStart = reports[0].t;

        for (int i = 0 ; i < N ; i++) {
//            int nowEnd = reports[i].t;
//            if (recentStart <= nowEnd) {
//                nowEnd = recentStart-1;
//            }
//
//            answer = Math.max(answer, recentStart - nowEnd - 1);
//            recentStart = nowEnd - reports[i].d + 1;
            if (reports[i].t <= recentStart){
                recentStart = reports[i].t - reports[i].d;
            }
            else {
                recentStart -= reports[i].d;
            }
        }

        answer = recentStart;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            reports[i] = new Report(d, t);
        }
    }

    static void init() {
        reports = new Report[N];
    }
}