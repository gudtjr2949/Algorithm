package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 1167 : 트리의 지름
public class BOJ_1167 {

    static int V, answer;
    static int end; // 가장 먼 정점
    static ArrayList<ArrayList<Point>> list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        V = Integer.parseInt(st.nextToken());

        answer = Integer.MIN_VALUE;

        // 인접리스트 생성
        for (int i = 0 ; i <= V ; i++)
            list.add(new ArrayList<>());


        for (int i = 1 ; i <= V ; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());

            while (true) {
                int end = Integer.parseInt(st.nextToken());

                if (end == -1) {
                    break;
                }

                int dis = Integer.parseInt(st.nextToken());

                list.get(start).add(new Point(end, dis));
                list.get(end).add(new Point(start, dis));
            }
        }

        visited = new boolean[V + 1];
        dfs(1, 0);

        visited = new boolean[V + 1];
        dfs(end, 0);

        System.out.println(answer);
    }

    /*
    start : 시작점
    dis : 현재까지 총 거리
    */
    private static void dfs(int start, int dis) {
        // 1에서 시작했을 때, 가장 먼 정점 찾기
        if (answer < dis) {
            end = start;
            answer = dis;
        }

        visited[start] = true;

        for (int i = 0 ; i < list.get(start).size() ; i++) {
            if (!visited[list.get(start).get(i).end]) {
                visited[list.get(start).get(i).end] = true;
                dfs(list.get(start).get(i).end, dis + list.get(start).get(i).dis);
            }
        }
    }


    static class Point {
        int end, dis;

        public Point(int end, int dis) {
            this.end = end;
            this.dis = dis;
        }
    }
}
