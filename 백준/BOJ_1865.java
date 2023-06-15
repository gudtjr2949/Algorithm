package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 1865 : 웜홀
public class BOJ_1865 {

    static int N, M, W;
    static ArrayList<ArrayList<Node>> list;
    static int[] dist;
    static final int MAX = 10001;

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

        for (int num = 0; num < TC; num++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();

            dist = new int[N + 1];
            Arrays.fill(dist, MAX);


            for (int i = 0; i <= N; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < M + W; i++) {
                st = new StringTokenizer(bf.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                // 도로
                if (i < M) {
                    list.get(S).add(new Node(E, T));
                    list.get(E).add(new Node(S, T));
                }
                // 웜홀
                else {
                    list.get(S).add(new Node(E, (T * -1)));
                }
            }

            if (BellmanFord()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }

    static boolean BellmanFord() {

        // 1부터 시작
        dist[1] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int start = j;
                for (int q = 0; q < list.get(j).size(); q++) {
                    int end = list.get(j).get(q).end;
                    int time = list.get(j).get(q).time;

                    if (dist[end] > dist[start] + time) {
                        dist[end] = dist[start] + time;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                int end = list.get(i).get(j).end;
                int time = list.get(i).get(j).time;

                // dist 배열 갱신
                if (dist[end] > dist[i] + time) {
                    return true;
                }
            }
        }


        return false;
    }
}
