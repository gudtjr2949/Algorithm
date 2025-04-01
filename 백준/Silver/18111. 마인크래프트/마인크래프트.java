import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, B, max, answerTime, answerHeight;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answerTime + " " + answerHeight);
    }

    static void solve() {
        answerTime = Integer.MAX_VALUE;
        answerHeight = max;

        for (int i = max ; i >= 0 ; i--) {
            setFlatly(i);
        }
    }

    static void setFlatly(int h) {
        int time = 0;
        int b = B;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] > h) {
                    int diff = map[i][j] - h;
                    time += diff * 2;
                    b += diff;
                } else if (map[i][j] < h) {
                    int diff = h - map[i][j];
                    time += diff;
                    b -= diff;
                }
            }
        }

        if (b < 0) return;

        if (answerTime > time) {
            answerHeight = h;
            answerTime = time;
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
    }

    static void init() {
        map = new int[N][M];
    }
}