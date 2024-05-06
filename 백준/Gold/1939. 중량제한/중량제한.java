import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, fromIsland, toIsland, maxWeight;
    static List<List<Island>> list;
    static boolean[] visited;

    static class Island {
        int to, weight;

        public Island (int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 0;

        for (int i = 0 ; i < N+1 ; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(from).add(new Island (to, weight));
            list.get(to).add(new Island(from, weight));

            maxWeight = Math.max(maxWeight, weight);
        }

        right = maxWeight;

        st = new StringTokenizer(bf.readLine());
        fromIsland = Integer.parseInt(st.nextToken());
        toIsland = Integer.parseInt(st.nextToken());

        while (left <= right) {
            int mid = (left + right) / 2;

            visited = new boolean[N+1];

            if (bfs(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }

    static boolean bfs(int mid) {
        Queue<Integer> Q = new LinkedList<>();

        Q.add(fromIsland);

        while (!Q.isEmpty()) {
            int from = Q.poll();

            visited[from] = true;

            if (from == toIsland) {
                return true;
            }

            for (int i = 0 ; i < list.get(from).size() ; i++) {
                int to = list.get(from).get(i).to;
                int weight = list.get(from).get(i).weight;

                if (!visited[to] && mid <= weight) {
                    visited[to] = true;
                    Q.add(to);
                }

            }
        }

        return false;
    }
}