package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 백준 1697 : 숨바꼭질
public class BOJ_1697 {

    static int N;
    static int K;
    static int answer;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String nk = bf.readLine();
        String[] nk_arr = nk.split(" ");

        N = Integer.parseInt(nk_arr[0]);
        K = Integer.parseInt(nk_arr[1]);
        visited = new boolean[100001];

        answer = 0;

        if (N < K) {
            bfs(N);
        } else {
            answer = N - K;
        }

        System.out.println(answer);
    }

    private static void bfs(int idx) {
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(idx, 0));

        int[] n_idx = {1, -1};

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0; i < size; i++) {
                Point p = Q.poll();

                if (p.idx == K) {
                    answer += p.depth;
                    return;
                }

                int d_idx = 0;

                visited[p.idx] = true;

                for (int j = 0 ; j < 3 ; j++) {
                    if (j == 2) {
                        d_idx *= 2;
                    }
                    else {
                        d_idx = p.idx + n_idx[j];
                    }

                    if (!(d_idx >= 100001 || d_idx < 0) && !visited[d_idx]) {
                        visited[d_idx] = true;
                        Q.add(new Point(d_idx, p.depth + 1));
                    }

                    d_idx = p.idx;
                }
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
