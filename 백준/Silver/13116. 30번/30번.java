import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static int[][] operations;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {
        for (int[] operation : operations) {
            int a = operation[0];
            int b = operation[1];
            if (findDepth(a) > findDepth(b)) {
                while (findDepth(a) != findDepth(b)) a = findParents(a);
            } else if (findDepth(a) < findDepth(b)) {
                while (findDepth(a) != findDepth(b)) b = findParents(b);
            }

            while (a != b) {
                a = findParents(a);
                b = findParents(b);
            }

            sb.append(10 * a).append("\n");
        }
    }

    static int findDepth(int idx) {
        int depth = 0;
        for (int i = 0 ; i <= 9 ; i++) {
            if (Math.pow(2, i) <= idx && Math.pow(2, i+1) > idx) {
                depth = i;
                break;
            }
        }
        return depth;
    }

    static int findParents(int idx) {
        if (idx == 1) return 1;
        return idx / 2;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < T ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            operations[i][0] = a;
            operations[i][1] = b;
        }
    }

    static void init() {
        operations = new int[T][2];
    }
}