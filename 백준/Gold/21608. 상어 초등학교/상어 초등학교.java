import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] index, dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static List<List<Integer>> friends;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];
        index = new int[N*N+1];
        friends = new ArrayList<>();

        for (int i = 0 ; i <= N*N ; i++) {
            friends.add(new ArrayList<>());
        }

        for (int i = 0 ; i < N*N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int idx = Integer.parseInt(st.nextToken());

            index[i] = idx;

            for (int j = 0 ; j < 4 ; j++) {
                int liked = Integer.parseInt(st.nextToken());
                friends.get(idx).add(liked);
            }
        }

        for (int i = 0 ; i < N*N ; i++) {
            solve(index[i]);
        }

        findAnswer();

        System.out.println(answer);
    }

    static void findAnswer() {
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                int cnt = 0;

                for (int k = 0 ; k < 4 ; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];

                    if (!isRange(nx, ny)) continue;

                    if (friends.get(map[i][j]).contains(map[ny][nx])) cnt++;
                }

                if (cnt != 0) answer += Math.pow(10, cnt-1);
            }
        }
    }

    static void solve(int student) {
        int maxEmpty = 0;
        int maxLiked = 0;
        int x = N+1;
        int y = N+1;


        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {

                if (map[i][j] != 0) continue;

                int liked = 0;
                int empty = 0;

                // 주변에 있는 좋아하는 학생 수
                for (int k = 0 ; k < 4 ; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];

                    if (!isRange(nx, ny)) continue;

                    if (friends.get(student).contains(map[ny][nx])) liked++;
                    else if (map[ny][nx] == 0) empty++;
                }

                if (maxLiked < liked) { // 조건 1
                    maxLiked = liked;
                    maxEmpty = empty;
                    x = j;
                    y = i;
                } else if (maxLiked == liked) {
                    if (maxEmpty < empty) { // 조건 2
                        maxEmpty = empty;
                        x = j;
                        y = i;
                    } else if (maxEmpty == empty) {
                        if (i < y) { // 조건 3
                            y = i;
                            x = j;
                        } else if (i == y) {
                            if (j < x) { // 조건 3
                                x = j;
                            }
                        }
                    }
                }
            }
        }


        map[y][x] = student;
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}