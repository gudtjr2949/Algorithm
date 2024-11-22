import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parents, plan;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        parents = new int[N+1];
        for (int i = 0 ; i <= N ; i++) parents[i] = i;

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1 ; j <= N ; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    union(i, j);
                }
            }
        }

        plan = new int[M];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < M ; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        if (solve()) System.out.println("YES");
        else System.out.println("NO");
    }

    static boolean solve() {
        for (int i = 1 ; i < M ; i++) {
            if (parents[plan[i]] != parents[plan[i-1]]) {
                return false;
            }
        }

        return true;
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (a > b) parents[a] = b;
        else parents[b] = a;
    }

    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }
}