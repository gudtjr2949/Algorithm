import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] arr, size;
    static boolean[][][] visited;
    static List<Integer> answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        arr = new int[3];
        size = new int[3];
        answer = new ArrayList<>();

        for (int i = 0 ; i < 3 ; i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[size[0]+1][size[1]+1][size[2]+1];
        arr[2] = size[2];

        dfs();

        Collections.sort(answer);

        for (Integer num : answer) System.out.print(num + " ");
    }

    static void dfs() {
        if (visited[arr[0]][arr[1]][arr[2]]) return;

        visited[arr[0]][arr[1]][arr[2]] = true;

        if (arr[0] == 0) answer.add(arr[2]);

        int preA = arr[0];
        int preB = arr[1];
        int preC = arr[2];

        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;

                movingWater(i, j);
                dfs();
                restore(preA, preB, preC);
            }
        }
    }

    static void restore(int preA, int preB, int preC) {
        arr[0] = preA;
        arr[1] = preB;
        arr[2] = preC;
    }

    static void movingWater(int from, int to) {
        if (size[to] - arr[to] >= arr[from]) {
            arr[to] += arr[from];
            arr[from] = 0;
        } else {
            arr[from] -= size[to] - arr[to];
            arr[to] = size[to];
        }
    }
}