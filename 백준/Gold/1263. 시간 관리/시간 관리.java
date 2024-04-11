import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Work[] works;
    static class Work implements Comparable<Work> {
        int T, S;

        public Work(int t, int s) {
            T = t;
            S = s;
        }

        @Override
        public String toString() {
            return "Work{" +
                    "T=" + T +
                    ", S=" + S +
                    '}';
        }

        @Override
        public int compareTo(Work w) {
            return w.S - this.S;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        works = new Work[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            works[i] = new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(works);

        System.out.println(solve());
    }

    static int solve() {
        int curTime = works[0].S;

        for (int i = 0 ; i < N ; i++) {
            if (curTime > works[i].S) {
                curTime = works[i].S - works[i].T;
            } else {
                curTime -= works[i].T;
            }
        }

        if (curTime < 0) {
            return -1;
        }

        return curTime;
    }
}