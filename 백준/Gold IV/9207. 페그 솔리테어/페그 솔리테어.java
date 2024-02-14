import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, minPin, minMoveCnt;
    static char[][] map;
    static int[] ny = {-1, 0, 1, 0}, nx = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(bf.readLine());

        for (int Test = 0 ; Test < N ; Test++) {
            map = new char[5][9];
            minPin = Integer.MAX_VALUE;
            minMoveCnt = 0;

            int cnt = 0;

            for (int i = 0; i < 5; i++) {
                String s = bf.readLine();
                for (int j = 0; j < 9; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == 'o') cnt++;
                }
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map[i][j] == 'o') {
                        dfs(0, cnt);
                    }
                }
            }

            sb.append(minPin).append(" ").append(minMoveCnt).append("\n");

            if (Test < N-1) {
                String s = bf.readLine();
            }
        }

        System.out.println(sb);
    }

    static void dfs(int moveCnt, int nowPin) {
        for (int i = 0 ; i < 5 ; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 'o') {
                    for (int q = 0; q < 4; q++) {
                        int dy = i + ny[q];
                        int dx = j + nx[q];
                        int ddy = dy + ny[q];
                        int ddx = dx + nx[q];

                        if (dx >= 0 && dx < 9 && dy >= 0 && dy < 5 && ddx >= 0 && ddx < 9 && ddy >= 0 && ddy < 5
                                && map[dy][dx] == 'o' && map[ddy][ddx] == '.') {
                            map[i][j] = '.';
                            map[dy][dx] = '.';
                            map[ddy][ddx] = 'o';
                            dfs(moveCnt + 1, nowPin - 1);
                            map[i][j] = 'o';
                            map[dy][dx] = 'o';
                            map[ddy][ddx] = '.';
                        }
                    }
                }
            }
        }

        if (minPin > nowPin) {
            minPin = nowPin;
            minMoveCnt = moveCnt;
        }
    }
}