import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        parents = new int[N+1];

        for (int i = 0 ; i <= N ; i++) parents[i] = i;

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            for (int j = 1 ; j <= N ; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());

        boolean possible = true;

        int first = Integer.parseInt(st.nextToken());

        for (int i = 1 ; i < M ; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (parents[first] != parents[next]) {
                possible = false;
                break;
            }
        }

        System.out.println(possible ? "YES" : "NO");
    }

    static void union(int i, int j) {
        i = find(i);
        j = find(j);

        if (i < j) {
            parents[j] = i;
        } else {
            parents[i] = j;
        }
    }

    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }
}