import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static boolean possible;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(answer);
    }

    static void solve(int a, int b) {
        if (find(a) == find(b)) {
            possible = true;
            return;
        }

        if (a < b) union(a, b);
        else union(b, a);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) parents[b] = a;
    }

    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            solve(a, b);
            if (possible) {
                answer = i+1;
                break;
            }
        }
    }


    static void init() {
        parents = new int[N];
        for (int i = 0 ; i < N ; i++) parents[i] = i;
    }
}