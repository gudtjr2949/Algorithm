package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1238 : 파티
public class BOJ_1238 {

    static class Node {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static int N, M, X;
    static ArrayList<Node>[] back_adj, go_adj;
    static boolean[] go_visited, back_visited;
    static int[] go, back;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        back_adj = new ArrayList[N+1];
        go_adj = new ArrayList[N+1];

        go = new int[N+1];
        back = new int[N+1];

        go_visited = new boolean[N+1];
        back_visited = new boolean[N+1];

        for (int i = 0 ; i < N+1 ; i++) {
            go_adj[i] = new ArrayList<>();
            back_adj[i] = new ArrayList<>();
        }

        Arrays.fill(go, Integer.MAX_VALUE);
        Arrays.fill(back, Integer.MAX_VALUE);

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            go_adj[e].add(new Node(s, c));
            back_adj[s].add(new Node(e, c));
        }

        go_solve();
        back_solve();

        int answer = Integer.MIN_VALUE;

        for (int i = 1 ; i < N+1 ; i++) {
            if (i != X) {
                answer = Math.max(answer, go[i] + back[i]);
            }
        }

        System.out.println(answer);
    }

    private static void go_solve() {
        go[X] = 0;

        for (int i = 0 ; i < N ; i++) {
            int minIdx = -1;
            int minD = Integer.MAX_VALUE;

            for (int j = 1 ; j < N+1 ; j++) {
                if (minD > go[j] && !go_visited[j]) {
                    minD = go[j];
                    minIdx = j;
                }
            }

            if (minIdx == -1) {
                break;
            }

            go_visited[minIdx] = true;

            for (Node next : go_adj[minIdx]) {
                if (go[next.end] > go[minIdx] + next.cost && !go_visited[next.end]) {
                    go[next.end] = go[minIdx] + next.cost;
                }
            }
        }
    }

    private static void back_solve() {
        back[X] = 0; // X에서 출발

        for (int i = 0 ; i < N ; i++) {
            int minIdx = -1;
            int minD = Integer.MAX_VALUE;

            for (int j = 1 ; j < N+1 ; j++) {
                if (minD > back[j] && !back_visited[j]) {
                    minD = back[j];
                    minIdx = j;
                }
            }

            if (minIdx == -1) {
                break;
            }

            back_visited[minIdx] = true;

            for (Node next : back_adj[minIdx]) {
                if (back[next.end] > back[minIdx] + next.cost && !back_visited[next.end]) {
                    back[next.end] = back[minIdx] + next.cost;
                }
            }
        }
    }
}
