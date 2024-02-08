import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, answer = 0;
    static List<Integer> W = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            W.add(Integer.parseInt(st.nextToken()));
        }

        dfs(N, 0);

        System.out.println(answer);
    }

    static void dfs(int size, int sum) {
        if (size == 2) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 1 ; i < W.size()-1 ; i++) {
            int tmpSum = W.get(i-1) * W.get(i+1);
            int remove = W.remove(i);
            dfs(size-1, sum+tmpSum);
            W.add(i, remove);
        }
    }
}