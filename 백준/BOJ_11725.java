package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 11725 : 트리의 부모 찾기
public class BOJ_11725 {

    static int N;
    static int[] input;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        list = new ArrayList<>();

        visited = new boolean[N+1];

        input = new int[N+1];

        for (int i = 0 ; i <= N ; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0 ; i < N-1 ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            list.get(num1).add(num2);
            list.get(num2).add(num1);
        }

        dfs(1);

        for (int i = 2 ; i <= N ; i++) {
            System.out.println(input[i]);
        }
    }

    private static void dfs(int num) {

        visited[num] = true;

        for (int i = 0 ; i < list.get(num).size() ; i++) {
            if (!visited[list.get(num).get(i)]) {
                input[list.get(num).get(i)] = num;
                dfs(list.get(num).get(i));
            }
        }
    }
}
