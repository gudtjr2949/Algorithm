package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준 10282 : 해킹
public class BOJ_10282 {

    static int n, d, c, cnt, second;
    static ArrayList<ArrayList<Node>> list;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 1 ; T <= Test ; T++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dist = new int[n+1];

            cnt = 0;
            second = 0;

            Arrays.fill(dist, Integer.MAX_VALUE);

            list = new ArrayList<>();

            for (int i = 0 ; i < n+1 ; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0 ; i < d ; i++) {
                st = new StringTokenizer(bf.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list.get(b).add(new Node(a, s));
            }

            solve();

            findAns();

            sb.append(cnt).append(" ").append(second).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve() {
        PriorityQueue<Node> PQ = new PriorityQueue<>();

        boolean[] visited = new boolean[n+1];

        PQ.add(new Node(c, 0));
        dist[c] = 0;
        visited[c] = true;

        while (!PQ.isEmpty()) {

            Node node = PQ.poll();

            int from = node.to;

            for (int i = 0 ; i < list.get(from).size() ; i++) {
                int to = list.get(from).get(i).to;
                int cost = list.get(from).get(i).cost;

                if (!visited[to]) {
                    dist[to] = dist[from] + cost;
                    visited[to] = true;
                    PQ.add(new Node(to, cost));
                } else {
                    if (dist[to] > dist[from] + cost) {
                        dist[to] = dist[from] + cost;
                        PQ.add(new Node(to, cost));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    private static void findAns() {
        for (int i = 1 ; i < n+1 ; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                cnt++;
                second = Math.max(second, dist[i]);
            }
        }
    }
}
