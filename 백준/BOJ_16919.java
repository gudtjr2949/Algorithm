package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 16919 : 봄버맨 2
public class BOJ_16919 {

    static int R, C, N;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        char[][] copy_map = new char[R][C];

        sb = new StringBuilder();

        for (int i = 0 ; i < R ; i++) {
            String s = bf.readLine();
            Arrays.fill(copy_map[i], 'O');

            for (int j = 0 ; j < C ; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int[] nx = {0, 1, 0, -1};
        int[] ny = {-1, 0, 1, 0};

        if (N == 1) {
            print(map);
        } else if (N % 2 == 0) { // 꽉 채우기
            for (int i = 0 ; i < R ; i++) {
                Arrays.fill(map[i], 'O');
            }
            print(map);
        } else if (N % 4 == 3) { // 초기 폭탄 폭발
            for (int i = 0 ; i < R ; i++) {
                for (int j = 0 ; j < C ; j++) {
                    if (map[i][j] == 'O') {

                        copy_map[i][j] = '.';

                        for (int q = 0 ; q < 4 ; q++) {
                            int dx = j + nx[q];
                            int dy = i + ny[q];

                            if (dx >= 0 && dx < C && dy >= 0 && dy < R) {
                                copy_map[dy][dx] = '.';
                            }
                        }
                    }
                }
            }

            print(copy_map);
        } else if (N % 4 == 1) { // 후기 폭탄 폭발
            for (int i = 0 ; i < R ; i++) {
                for (int j = 0 ; j < C ; j++) {
                    if (map[i][j] == 'O') {

                        copy_map[i][j] = '.';

                        for (int q = 0 ; q < 4 ; q++) {
                            int dx = j + nx[q];
                            int dy = i + ny[q];

                            if (dx >= 0 && dx < C && dy >= 0 && dy < R) {
                                copy_map[dy][dx] = '.';
                            }
                        }
                    }
                }
            }

            for (int i = 0 ; i < R ; i++) {
                for (int j = 0 ; j < C ; j++) {
                    map[i][j] = copy_map[i][j];
                }
            }
            for (int i = 0 ; i < R ; i++) {
                Arrays.fill(copy_map[i], 'O');
            }

            for (int i = 0 ; i < R ; i++) {
                for (int j = 0 ; j < C ; j++) {
                    if (map[i][j] == 'O') {

                        copy_map[i][j] = '.';

                        for (int q = 0 ; q < 4 ; q++) {
                            int dx = j + nx[q];
                            int dy = i + ny[q];

                            if (dx >= 0 && dx < C && dy >= 0 && dy < R) {
                                copy_map[dy][dx] = '.';
                            }
                        }
                    }
                }
            }

            print(copy_map);
        }

        System.out.println(sb);
    }


    private static void print(char[][] arr) {
        for (int i = 0 ; i < R ; i++) {
            for (int j = 0 ; j < C ; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
    }
}
