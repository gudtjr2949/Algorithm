import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Line[] lines;
    static class Line implements Comparable<Line> {
        int x, y;

        public Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Line n) {
            if (this.x == n.x) return this.y - n.y;
            else return this.x - n.x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        lines = new Line[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            lines[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(lines);

        System.out.println(solve());
    }

    static long solve() {
        long sum = 0;

        int preX = lines[0].x;
        int preY = lines[0].y;

        sum = preY - preX;

        for (int i = 1 ; i < N ; i++) {
            int nowX = lines[i].x;
            int nowY = lines[i].y;

            if (nowX >= preX && nowY <= preY) continue;
            else if (nowX > preY) sum += nowY - nowX;
            else if (nowX <= preY && nowY > preY) sum += nowY - preY;

            preX = nowX;
            preY = nowY;
        }

        return sum;
    }
}