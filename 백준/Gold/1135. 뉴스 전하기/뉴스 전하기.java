import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(solve(0));
    }

    static int solve(int idx) {
        List<Integer> list = new ArrayList<>();
        int result = 0;
        int w = adj.get(idx).size();

        for (int child : adj.get(idx)) {
            list.add(solve(child));
        }

        Collections.sort(list);

        for (int next : list) {
            result = Math.max(result, next + w--);
        }

        return result;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) continue;
            else {
                adj.get(parent).add(i);
            }
        }
    }

    static void init() {
        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) adj.add(new ArrayList<>());
    }
}