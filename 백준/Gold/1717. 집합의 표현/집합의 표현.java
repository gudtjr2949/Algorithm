import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parents;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        for (int i = 0 ; i <= N ; i++) parents[i] = i;

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (operation == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
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