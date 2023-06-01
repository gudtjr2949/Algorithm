package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1922 : 네트워크 연결
public class BOJ_1922 {

    static int N, M, answer;
    static int[] parents;
    static ArrayList<Edge> adj;

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        answer = 0;

        adj = new ArrayList<>();

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj.add(new Edge(s, e, c));
        }

        Collections.sort(adj);

        // makeSet
        parents = new int[N+1];
        for (int i = 1 ; i < N+1 ; i++) {
            parents[i] = i;
        }

        int sum = 0;

        for (int i = 0 ; i < adj.size() ; i++) {
            Edge e = adj.get(i);

            // 대표값이 다르기 때문에 연결해주고,
            if (find(e.start) != find(e.end)) {
                union(e.start, e.end);
                sum += e.cost; // 어차피 오름차순으로 정렬해줬으니까 가중치 더해주면 됨
            }
        }

        System.out.println(sum);
    }
    
    // 대표값 찾기
    private static int find(int num) {
        if (parents[num] == num) {
            return num;
        }
        else {
            return parents[num] = find(parents[num]);
        }
    }
    
    // 대푯값이 다르면? 연결하기
    private static void union(int a,int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parents[pb] = pa;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
