package coding_test.백준;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 15686 : 치킨 배달
public class BOJ_15686 {

    static int N;
    static int M;
    static int[][] board;
    static ArrayList<Point> Chicken;
    static ArrayList<Point> Home;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String nm = bf.readLine();
        String[] nm_arr = nm.split(" ");

        N = Integer.parseInt(nm_arr[0]);
        M = Integer.parseInt(nm_arr[1]);
        board = new int[N][N];

        Chicken = new ArrayList<>();
        Home = new ArrayList<>();

        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(s_arr[j]);
                if (board[i][j] == 1) {
                    Home.add(new Point(j, i));
                } else if (board[i][j] == 2) {
                    Chicken.add(new Point(j, i));
                }
            }
        }

        for (int i = M ; i >= 1 ; i--) {
            Select_Chicken(new int[i], new boolean[Chicken.size()], 0, 0);
        }

        System.out.println(answer);
    }

    // 열려있는 치킨집 정하기
    private static void Select_Chicken(int[] input, boolean[] visited, int idx, int cur) {
        if (idx == input.length) {
            Find_Distance(input);
            return;
        }

        for (int i = cur; i < Chicken.size(); i++) {
            visited[i] = true;
            input[idx] = i;
            Select_Chicken(input, visited, idx + 1, i + 1);
            visited[i] = false;
        }
    }

    // 도시의 치킨 거리 계산
    private static void Find_Distance(int[] input) {
        int distance = 0;

        for (int i = 0 ; i < Home.size() ; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0 ; j < input.length ; j++) {
                int dis = Math.abs(Home.get(i).x - Chicken.get(input[j]).x) + Math.abs(Home.get(i).y - Chicken.get(input[j]).y); // i번째 집과 input[j]번째 치킨집 사이 거리

                min = Math.min(min, dis); // i번째 집과 input[j]번째 치킨집 사이 거리 중 가장 짧은 거리 찾기
            }

            distance += min; // i번째 집과 input[j]번째 치킨집 사이 거리 중 가장 짧은 거리를 찾았으면 모든 집의 총 이동거리에 해당 거리 더하기
        }

        answer = Math.min(answer, distance);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
