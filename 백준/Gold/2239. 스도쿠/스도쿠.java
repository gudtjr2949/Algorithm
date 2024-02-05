import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int[][] map;
    static boolean findAnswer;
    static List<Point> list = new ArrayList<>();
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];

        for (int i = 0 ; i < 9 ; i++) {
            String s = bf.readLine();
            for (int j = 0 ; j < 9 ; j++) {
                map[i][j] = s.charAt(j) - '0';

                if (map[i][j] == 0) {
                    list.add(new Point(j, i));
                }
            }
        }

        findAnswer = false;

        dfs(0);
    }

    static void dfs(int idx) {
        if (idx != 0 && !isPossible(idx-1)) {
            return;
        }

        if (idx == list.size()) {
            printAnswer();
            findAnswer = true;
            return;
        }

        int x = list.get(idx).x;
        int y = list.get(idx).y;

        for (int i = 1 ; i <= 9 ; i++) {
            map[y][x] = i;
            dfs(idx+1);
            if (findAnswer) {
                return;
            }
            map[y][x] = 0;
        }
    }

    static boolean isPossible(int idx) {
        int x = list.get(idx).x;
        int y = list.get(idx).y;

        // 가로
        for (int i = 0 ; i < 9 ; i++) {
            if (x != i && map[y][x] == map[y][i]) {
                return false;
            }
        }

        // 세로
        for (int i = 0 ; i < 9 ; i++) {
            if (y != i && map[y][x] == map[i][x]) {
                return false;
            }
        }

        // 사각형
        int dx = (x / 3) * 3;
        int dy = (y / 3) * 3;

        for (int i = dy; i < dy + 3; i++) {
            for (int j = dx; j < dx + 3; j++) {
                if (i != x && i != y && map[i][j] == map[y][x]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void printAnswer() {
        for (int i = 0 ; i < 9 ; i++) {
            for (int j = 0 ; j < 9 ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}