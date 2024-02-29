import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, idx = 0;
    static int[] edgeCnt;
    static double[] blood;
    static Map<String, Integer> map = new HashMap<>();
    static List<List<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        blood = new double[N*2+1];
        edgeCnt = new int[N*2+1];
        String[] candidate = new String[M];

        for (int i = 0 ; i <= N*2 ; i++) {
            adj.add(new ArrayList<>());
        }

        String root = bf.readLine();
        map.put(root, idx);

        blood[idx] = 1.0;

        idx++;

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(bf.readLine());
            String child = st.nextToken();
            String parents1 = st.nextToken();
            String parents2 = st.nextToken();
            setMap(child, parents1, parents2);
            adj.get(map.get(parents1)).add(map.get(child));
            adj.get(map.get(parents2)).add(map.get(child));
            edgeCnt[map.get(child)]+=2;
        }

        for (int i = 0 ; i < M ; i++) {
            candidate[i] = bf.readLine();
        }

        solve();

        String answer = "";
        double max = 0.0;

        for (int i = 0 ; i < M ; i++) {
            if (map.get(candidate[i]) == null) continue;

            if (max < blood[map.get(candidate[i])]) {
                max = blood[map.get(candidate[i])];
                answer = candidate[i];
            }
        }

        System.out.println(answer);
    }

    static void solve() {
        Queue<Integer> Q = new LinkedList<>();

        for (int i = 0 ; i < idx ; i++) {
            if (edgeCnt[i] == 0) Q.add(i);
        }

        while (!Q.isEmpty()) {
            int now = Q.poll();

            for (int next : adj.get(now)) {
                edgeCnt[next]--;
                blood[next] = Math.max(blood[next], blood[now]/2 + blood[next]);
                if (edgeCnt[next] == 0) {
                    Q.add(next);
                }
            }
        }
    }

    static void setMap(String child, String parents1, String parents2) {
        if (!map.containsKey(child)) {
            map.put(child, idx);
            idx++;
        }

        if (!map.containsKey(parents1)) {
            map.put(parents1, idx);
            idx++;
        }

        if (!map.containsKey(parents2)) {
            map.put(parents2, idx);
            idx++;
        }
    }
}