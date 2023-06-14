package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 1865 : 웜홀
public class BOJ_1865 {

    static int N, M, W, start;
    static ArrayList<ArrayList<Node>> bridge;
    static ArrayList<ArrayList<Node>> wormhole;
    static boolean possible;

    static class Node {
        int end, time;

        public Node(int end, int time) {
            this.end = end;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "end=" + end +
                    ", time=" + time +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(bf.readLine());

        for (int num = 0 ; num < TC ; num++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            bridge = new ArrayList<>();
            wormhole = new ArrayList<>();

            for (int i = 0 ; i <= N ; i++) {
                bridge.add(new ArrayList<>());
                wormhole.add(new ArrayList<>());
            }

            // 도로
            for (int i = 0 ; i < M ; i++) {
                st = new StringTokenizer(bf.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                for (int j = 0 ; j < bridge.get(S).size() ; j++) {
                    if (bridge.get(S).get(j).end == E) {
                        if (bridge.get(S).get(j).time > T) {
                            bridge.get(S).remove(j);
                        } else {
                            break;
                        }
                    }
                }

                bridge.get(S).add(new Node(E, T));

                for (int j = 0 ; j < bridge.get(E).size() ; j++) {
                    if (bridge.get(E).get(j).end == S) {
                        if (bridge.get(E).get(j).time > T) {
                            bridge.get(E).remove(j);
                        } else {
                            break;
                        }
                    }
                }

                bridge.get(E).add(new Node(S, T));
            }

            for (int i = 0 ; i < W ; i++) {
                st = new StringTokenizer(bf.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                for (int j = 0 ; j < wormhole.get(S).size() ; j++) {
                    if (wormhole.get(S).get(j).end == E) {
                        if (wormhole.get(S).get(j).time > T) {
                            wormhole.get(S).remove(j);
                        } else {
                            break;
                        }
                    }
                }

                wormhole.get(S).add(new Node(E, T));
            }

            possible = false;

            for (int i = 1 ; i <= N ; i++) {
                start = i;
                dfs(i, 0, new boolean[N+1]);

                if (possible) {
                    break;
                }
            }

            if (possible) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int cur, int time, boolean[] visited) {
        // 다시 처음으로 되돌아온 경우
        if (cur == start && visited[cur]) {
            if (time < 0) {
                possible = true;
                return;
            }
        }

        // 도로로 이동 가능한 경우
        for (int i = 0 ; i < bridge.get(cur).size() ; i++) {
            if (!visited[bridge.get(cur).get(i).end]) {
                visited[bridge.get(cur).get(i).end] = true;
                dfs(bridge.get(cur).get(i).end, time + bridge.get(cur).get(i).time, visited);
            }
        }

        // 웜홀로 이동 가능한 경우
        for (int i = 0 ; i < wormhole.get(cur).size() ; i++) {
            if (!visited[wormhole.get(cur).get(i).end]) {
                visited[wormhole.get(cur).get(i).end] = true;
                dfs(wormhole.get(cur).get(i).end, time - wormhole.get(cur).get(i).time, visited);
            }
        }
    }
}
