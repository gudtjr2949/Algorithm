package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1068 : 트리
public class BOJ_1068 {

    static int N, start, cnt;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        cnt = 0;

        arr = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int root = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (arr[i] == -1) {
                root = i;
            }
        }

        start = Integer.parseInt(bf.readLine());


        delete(start);

        count(root);

        System.out.println(cnt);
    }

    public static void delete(int s) {

        arr[s] = -2;

        for (int i = 0; i < N; i++) {
            if (arr[i] == s) {
                delete(i);
                arr[i] = -2;
            }
        }
    }

    public static void count(int s) {
        boolean leaf = true;
        visited[s] = true;

        for (int i = 0; i < N; i++) {
            if (arr[i] == s && !visited[i] && arr[s] != -2) {
                visited[i] = true;
                count(i);
                leaf = false;
            }
        }

        if (leaf && arr[s] != -2) {
            cnt++;
        }

    }
}
