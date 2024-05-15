import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, L;
    static Pool[] pools;
    static class Pool implements Comparable<Pool> {
        int start, end;

        public Pool(int x, int y) {
            this.start = x;
            this.end = y;
        }

        @Override
        public int compareTo(Pool n) {
            if (this.start == n.start) return n.end - this.end;
            else return this.start - n.start;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        pools = new Pool[N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            pools[i] = new Pool(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(pools);

        System.out.println(solve());
    }

    static long solve() {
        long cnt = 0;

        int prePlankEnd = pools[0].start;

        for (int i = 0 ; i < N ; i++) {
            // 현재 물 웅덩이가 이미 널빤지로 채워진 경우
            if (prePlankEnd >= pools[i].end) continue;

            if (prePlankEnd < pools[i].start) prePlankEnd = pools[i].start;

            while (true) {
                if (prePlankEnd + L < pools[i].end) {
                    cnt++;
                    prePlankEnd += L;
                } else {
                    cnt++;
                    prePlankEnd += L;
                    break;
                }
            }
        }

        return cnt;
    }
}