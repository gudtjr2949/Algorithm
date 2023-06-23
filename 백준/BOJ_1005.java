package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1005 : ACM Craft
public class BOJ_1005 {

    static int N, K, W, answer;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dist, build_time;

    static class Building implements Comparable<Building> {
        int dest, time;

        public Building(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Building{" +
                    "dest=" + dest +
                    ", time=" + time +
                    '}';
        }

        @Override
        public int compareTo(Building o) {
            return time - o.time;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        for (int Test = 0 ; Test < T ; Test++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            answer = 0;

            dist = new int[N+1];
            build_time = new int[N+1];

            list = new ArrayList<>();
            for (int i = 0 ; i <= N ; i++) {
                list.add(new ArrayList<>());
            }

            st = new StringTokenizer(bf.readLine());
            for (int i = 1 ; i <= N ; i++) {
                build_time[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0 ; i < K ; i++) {
                st = new StringTokenizer(bf.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                list.get(start).add(end);
                dist[end]++;
            }

            W = Integer.parseInt(bf.readLine());

            solve();

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void solve() {
        PriorityQueue<Building> Q = new PriorityQueue<>();

        // 가능한 출발 건물 찾기
        for (int i = 1 ; i <= N ; i++) {
            if (dist[i] == 0) {
                Q.add(new Building(i, build_time[i]));
            }
        }

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0 ; i < size ; i++) {
                Building b = Q.poll();

                // 목적지에 도착했고, 먼저 방문해야 하는 건물을 모두 방문했다면,
                if (b.dest == W && dist[W] == 0) {
                    answer = b.time;
                }

                for (int j = 0 ; j < list.get(b.dest).size() ; j++) {
                    dist[list.get(b.dest).get(j)]--;

                    // 먼저 방문해야하는 건물을 모두 방문했거나, 다음 이동 건물이 W인 경우,
                    if (dist[list.get(b.dest).get(j)] == 0 || list.get(b.dest).get(j) == W) {
                        Q.add(new Building(list.get(b.dest).get(j), b.time + build_time[list.get(b.dest).get(j)]));
                    }
                }
            }
        }
    }
}
