import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N, start;
    static int[] arr;
    static boolean[] visited;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N+1];
        visited = new boolean[N+1];
        list = new ArrayList<>();

        for (int i = 1 ; i <= N ; i++) {
            int num = Integer.parseInt(bf.readLine());
            arr[i] = num;
        }


        for (int i = 1 ; i <= N ; i++) {
            start = i;
            dfs(i);
        }

        System.out.println(list.size());
        for (Integer num : list) System.out.println(num);
    }

    static void dfs(int cur) {
        if (visited[cur]) {
            if (cur == start) list.add(start);
            return;
        }

        visited[cur] = true;
        dfs(arr[cur]);
        visited[cur] = false;
    }
}