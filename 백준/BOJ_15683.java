package coding_test.백준;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 15683 : 감시
public class BOJ_15683 {

    static int N;
    static int M;
    static int[][] map;

    // CCTV별 방향벡터
    static int[][][] cctv_nx = {{{0}, {1}, {0}, {-1}}
    , {{1, -1}, {0, 0}}
    , {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
    , {{-1, 0, 1}, {0, 1, 0}, {1, 0, -1}, {0, -1, 0}}
    , {{0, 1, 0, -1}}};

    static int[][][] cctv_ny = {{{-1}, {0}, {1}, {0}}
    , {{0, 0}, {-1, 1}}
    , {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}
    , {{0, -1, 0}, {-1, 0, 1}, {0, 1, 0}, {1, 0, -1}}
    , {{-1, 0, 1, 0}}};

    static ArrayList<Integer> list;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String nm = bf.readLine();
        String[] nm_arr = nm.split(" ");

        N = Integer.parseInt(nm_arr[0]);
        M = Integer.parseInt(nm_arr[1]);
        list = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        map = new int[N][M];

        for (int i = 0 ; i < N ; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(s_arr[j]);
                if (map[i][j] != 0 && map[i][j] != 6) {
                    list.add(map[i][j]);
                }
            }
        }

        dfs(0, new int[list.size()]);

        System.out.println(answer);
    }

    /*
    각각의 CCTV의 번호에 맞게 볼 수 있는 경우의 수 찾기
     */
    private static void dfs(int idx, int[] input) {
        if (idx == input.length) {
            solve(input);
            return;
        }

        for (int i = 0 ; i < cctv_nx[list.get(idx)-1].length ; i++) {
            input[idx] = i;
            dfs(idx + 1, input);
        }
    }

    private static void solve(int[] input) {

        int[][] copy_map = new int[N][M];

        for (int i = 0 ; i < N ; i++) { // map 복사
            for (int j = 0 ; j < M ; j++) {
                copy_map[i][j] = map[i][j];
            }
        }


        int idx = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (map[i][j] != 0 && map[i][j] != 6) { // CCTV 발견

                    for (int q = 0 ; q < cctv_nx[map[i][j]-1][input[idx]].length ; q++) { // 해당 CCTV에 해당하는 방향벡터 사용
                        int dx = j;
                        int dy = i;

                        for (int k = 1 ; k < Math.max(N, M) ; k++) {
                            dx = j + cctv_nx[map[i][j]-1][input[idx]][q] * k;
                            dy = i + cctv_ny[map[i][j]-1][input[idx]][q] * k;
                            if ((dx >= M || dx < 0 || dy >= N || dy < 0) || map[dy][dx] == 6) {
                                break;
                            } else {
                                copy_map[dy][dx] = -1; // 0 발견시 -1 처리
                            }
                        }
                    }

                    idx++; // 다음 CCTV로 넘어가기
                }
            }
        }


        count_zero(copy_map);
    }

    private static void count_zero(int[][] copy_map) {
        int cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (copy_map[i][j] == 0){ // 사각지대 카운트
                    cnt++;
                }
            }
        }

        answer = Math.min(answer, cnt);
    }
}
