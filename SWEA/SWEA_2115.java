package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// SWEA 2115 : 벌꿀채취
public class SWEA_2115 {

    static int N, M, C;
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 1; T <= Test; T++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            answer = Integer.MIN_VALUE;

            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            combi(0, 0, new int[2]);
            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    // 행 선택 -> 중복조합
    private static void combi(int idx, int cur, int[] input) {
        if (idx == 2) {
            make_Range(0, 0, new int[N][N], input);
            return;
        }

        for (int i = cur; i < N; i++) {
            input[idx] = i;
            combi(idx + 1, cur, input);
            cur++;
        }
    }

    // 범위 지정
    private static void make_Range(int idx, int cur, int[][] tmp_map, int[] input) {
        if (idx == 2) {
            makePoint(input, tmp_map, new ArrayList<>(), new ArrayList<>());
            return;
        }

        if (input[0] == input[1]) {
            for (int i = cur; i < N; i++) {
                if (tmp_map[input[idx]][i] == 0 && i + M <= N) {

                    for (int j = i; j < i + M; j++) {
                        tmp_map[input[idx]][j] = idx + 1;
                    }

                    make_Range(idx + 1, i + M, tmp_map, input);
                    tmp_map = restoration(idx, tmp_map, input, i);
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (tmp_map[input[idx]][i] == 0 && i + M <= N) {

                    for (int j = i; j < i + M; j++) {
                        tmp_map[input[idx]][j] = idx + 1;
                    }

                    make_Range(idx + 1, 0, tmp_map, input);
                    tmp_map = restoration(idx, tmp_map, input, i);
                }
            }
        }
    }

    // 첫번째 일꾼 채집
    private static void makePoint(int[] input, int[][] tmp_map, ArrayList<Point> point1, ArrayList<Point> point2) {

        for (int i = 0 ; i < N ; i++) {
            if (tmp_map[input[0]][i] == 1) {
                point1.add(new Point(i, input[0]));
            }
        }

        for (int i = 0 ; i < N ; i++) {
            if (tmp_map[input[1]][i] == 2) {
                point2.add(new Point(i, input[1]));
            }
        }

        combi_Point1(0, 0, new Point[point1.size()], point1, point2);
    }

    private static void combi_Point1(int idx, int cur, Point[] input, ArrayList<Point> point1, ArrayList<Point> point2) {
        if (idx == input.length) {
            combi_Point2(0, 0, input, new Point[point2.size()], point2);
            return;
        }

        for (int i = cur ; i < point1.size() ; i++) {
            input[idx] = point1.get(i);
            combi_Point1(idx+1, i+1, input, point1, point2);
            input[idx] = null;
            combi_Point1(idx+1, i+1,  input, point1, point2);
        }
    }

    private static void combi_Point2(int idx, int cur, Point[] input_1, Point[] input_2, ArrayList<Point> point2) {
        if (idx == input_2.length) {
            SumHoney(input_1, input_2);
            return;
        }

        for (int i = cur ; i < point2.size() ; i++) {
            input_2[idx] = point2.get(i);
            combi_Point2(idx+1, i+1, input_1, input_2, point2);
            input_2[idx] = null;
            combi_Point2(idx+1, i+1, input_1, input_2, point2);
        }
    }

    private static void SumHoney(Point[] input_1, Point[] input_2) {
        int sum_1 = 0;
        int size_1 = 0;

        int sum_2 = 0;
        int size_2 = 0;

        for (int i = 0 ; i < input_1.length ; i++) {
            if (input_1[i] != null) {
                sum_1 += map[input_1[i].y][input_1[i].x];
                size_1 += map[input_1[i].y][input_1[i].x] * map[input_1[i].y][input_1[i].x];
            }

            if (input_2[i] != null) {
                sum_2 += map[input_2[i].y][input_2[i].x];
                size_2 += map[input_2[i].y][input_2[i].x] * map[input_2[i].y][input_2[i].x];
            }
        }

        if (sum_1 <= C && sum_2 <= C) {
            answer = Math.max(answer, size_1 + size_2);
        }
    }

    private static int[][] restoration(int idx, int[][] tmp_map, int[] input, int x) {
        for (int i = x; i < x + M; i++) {
            tmp_map[input[idx]][i] = 0;
        }

        return tmp_map;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
