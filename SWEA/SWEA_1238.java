package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// SWEA 1238 : Contact
public class SWEA_1238 {

    static int N;
    static int start;
    static ArrayList<Integer>[] list;
    static int answer;
    static int[] depth_arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int T = 0 ; T < 10 ; T++) {
            String ns = bf.readLine();
            String[] ns_arr = ns.split(" ");
            N = Integer.parseInt(ns_arr[0]);
            start = Integer.parseInt(ns_arr[1]);
            answer = Integer.MIN_VALUE;
            depth_arr = new int[101];

            list = new ArrayList[101];

            for (int i = 0 ; i < 101 ; i++) {
                list[i] = new ArrayList<>();
            }

            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            for (int i = 0 ; i < N ; i+=2) {
                int from = Integer.parseInt(s_arr[i]);
                int to = Integer.parseInt(s_arr[i+1]);

                list[from].add(to);
            }

            bfs();
            findMax();

            sb.append("#").append(T+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(start, 0));
        depth_arr[start] = 0;

        boolean[] visited = new boolean[101];

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0 ; i < size ; i++) {
                Point p = Q.poll();

                visited[p.idx] = true;

                for (int j = 0; j < list[p.idx].size(); j++) {
                    int to = list[p.idx].get(j);
                    if (!visited[to]) {
                        visited[to] = true;
                        Q.add(new Point(to, p.depth+1));
                        depth_arr[to] = p.depth+1;
                    }
                }
            }
        }
    }

    private static void findMax() {
        int max = 0;

        for (int i = 1 ; i <= 100 ; i++) {
            max = Math.max(max, depth_arr[i]);
        }

        for (int i = 100 ; i >= 1 ; i--) {
            if (depth_arr[i] == max) {
                answer = i;
                break;
            }
        }
    }

    static class Point {
        int idx;
        int depth;

        public Point(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
}
