package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 17471 : 게리맨더링
public class BOJ_17471 {

    static int N;
    static ArrayList<Integer>[] list;
    static int[] population;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        list = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        population = new int[N + 1];

        String p = bf.readLine();
        String[] p_arr = p.split(" ");

        for (int i = 1; i < N + 1; i++) {
            population[i] = Integer.parseInt(p_arr[i - 1]);
        }

        for (int i = 1; i < N + 1; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            for (int j = 1; j <= Integer.parseInt(s_arr[0]); j++) {
                int end = Integer.parseInt(s_arr[j]);
                list[i].add(end);
            }
        }

        for (int i = 1 ; i < N ; i++) {
            combi(0,1, i, new boolean[N+1]);
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(answer);
        }
    }

    private static void combi(int idx, int cur, int size, boolean[] visited) {
        if (idx == size) {
            check(visited);
            return;
        }

        for (int i = cur; i < N + 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combi(idx + 1, i + 1, size, visited);
                visited[i] = false;
            }
        }
    }

    // 부분집합에서 만들어낸 boolean 배열을 사용해 A 선거구, B 선거구 만들기
    private static void check(boolean[] visited) {

        ArrayList<Integer> input_A = new ArrayList<>();

        ArrayList<Integer> input_B = new ArrayList<>();


        for (int i = 1 ; i < N+1 ; i++) {
            if (visited[i]) { // true 면 A 선거구
                input_A.add(i);
            }
            else {            // false 면 B 선거구
                input_B.add(i);
            }
        }
        
        if (bfs(input_A) && bfs(input_B)) {
            findDiff(input_A, input_B);
        }
    }
    
    // 만들어낸 선거구가 서로 연결되어 있는지 확인
    private static boolean bfs(ArrayList<Integer> input) {
        Queue<Integer> Q = new LinkedList<>();

        boolean[] visited = new boolean[N+1];

        Q.add(input.get(0));

        int cnt = input.size();

        while (!Q.isEmpty()) {
            int N = Q.poll();

            visited[N] = true;

            cnt--;

            for (int i = 0 ; i < list[N].size() ; i++) {
                if (!visited[list[N].get(i)] && input.contains(list[N].get(i))) {
                    visited[list[N].get(i)] = true;
                    Q.add(list[N].get(i));
                }
            }
        }

        if (cnt <= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private static void findDiff(ArrayList<Integer> input_A, ArrayList<Integer> input_B) {
        int sum_1 = 0;
        int sum_2 = 0;

        for (int i = 0 ; i < input_A.size() ; i++) {
            sum_1 += population[input_A.get(i)];
        }

        for (int i = 0 ; i < input_B.size() ; i++) {
            sum_2 += population[input_B.get(i)];
        }

        answer = Math.min(answer, Math.abs(sum_1 - sum_2));
    }
}