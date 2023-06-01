package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1260 : DFS와 BFS
public class BOJ_1260 {

    static int N;
    static int M;
    static int V;
    static ArrayList<Integer>[] list;
    static ArrayList<Integer> bfs_list;
    static ArrayList<Integer> dfs_list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String nmk = bf.readLine();
        String[] nmk_arr = nmk.split(" ");

        N = Integer.parseInt(nmk_arr[0]);
        M = Integer.parseInt(nmk_arr[1]);
        V = Integer.parseInt(nmk_arr[2]);
        list = new ArrayList[N+1];

        bfs_list = new ArrayList<>();
        dfs_list = new ArrayList<>();

        for (int i = 0 ; i < N+1 ; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0 ; i < M ; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            list[Integer.parseInt(s_arr[0])].add(Integer.parseInt(s_arr[1]));
            list[Integer.parseInt(s_arr[1])].add(Integer.parseInt(s_arr[0]));
        }

        for (int i = 0 ; i < N+1 ; i++) {
            Collections.sort(list[i]);
        }

        bfs(new boolean[N+1]);

        dfs_list.add(V);

        dfs(1, V, new boolean[N+1]);

        print();
    }

    private static void bfs(boolean[] visited) {
        Queue<Integer> Q = new LinkedList<>();

        Q.add(V);

        bfs_list.add(V);

        while (!Q.isEmpty()) {
            int size = Q.size();

            for (int i = 0 ; i < size ; i++) {
                int a = Q.poll();

                visited[a] = true;

                for (int j = 0 ; j < list[a].size() ; j++) {
                    if (!visited[list[a].get(j)]) {
                        bfs_list.add(list[a].get(j));
                        visited[list[a].get(j)] = true;
                        Q.add(list[a].get(j));
                    }
                }
            }
        }
    }

    private static void dfs(int idx, int cur, boolean[] visited) {
        if (idx == N) {
            return;
        }

        visited[cur] = true;

        for (int i = 0 ; i < list[cur].size() ; i++) {
            if (!visited[list[cur].get(i)]) {
                dfs_list.add(list[cur].get(i));
                visited[list[cur].get(i)] = true;
                dfs(idx+1, list[cur].get(i), visited);
            }
        }
    }

    private static void print() {
        for (int i = 0 ; i < dfs_list.size() ; i++) {
            System.out.print(dfs_list.get(i) + " ");
        }
        System.out.println();
        for (int i = 0 ; i < bfs_list.size() ; i++) {
            System.out.print(bfs_list.get(i) + " ");
        }
    }
}
