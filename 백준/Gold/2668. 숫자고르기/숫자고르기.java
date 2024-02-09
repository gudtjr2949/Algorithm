import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    static int N, start = 0;
    static boolean[] visited;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new int[N];
        visited = new boolean[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine()) - 1;
        }

        for (int i = 0 ; i < N ; i++) {
            visited[i] = true;
            start = i;
            dfs(i);
            visited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (Integer num : list) {
            System.out.println(num + 1);
        }
    }

    static void dfs(int idx) {
        if (arr[idx] == start) { // 시작한 수와 arr[idx] 와 같음 -> 리스트 담기
            list.add(arr[idx]);
            return;
        }

        if (!visited[arr[idx]]) {
            visited[arr[idx]] = true;
            dfs(arr[idx]);
            visited[arr[idx]] = false;
        }
    }

}