package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 1967 : 트리의 지름
public class BOJ_1967 {

    static int N, answer;
    static ArrayList<ArrayList<Node>> list;
    static boolean[] child;

    static class Node {
        int end, length;

        public Node(int end, int length) {
            this.end = end;
            this.length = length;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "end=" + end +
                    ", length=" + length +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        child = new boolean[N+1];

        list = new ArrayList<>();

        answer = Integer.MIN_VALUE;

        for (int i = 0 ; i <= N ; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 1 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, length));
            list.get(end).add(new Node(start, length));

            child[start] = true;
        }

        for (int i = 1 ; i <= N ; i++) {
            if (!child[i]) {
                dfs(i, 0, new boolean[N+1]);
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int start, int distance, boolean[] visited) {

        visited[start] = true;

        for (int i = 0 ; i < list.get(start).size() ; i++) {
            if (!visited[list.get(start).get(i).end]) {
                dfs(list.get(start).get(i).end, distance + list.get(start).get(i).length, visited);
            }
        }

        answer = Math.max(answer, distance);
    }
}
