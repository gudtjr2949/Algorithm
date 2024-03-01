import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, idx = 0;
    static int[] edgeCnt;
    static StringBuilder sb = new StringBuilder();
    static Map<String, Integer> nameMap = new HashMap<>();
    static Map<Integer, String> numMap = new HashMap<>();
    static List<List<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i <= 2*N ; i++) {
            adj.add(new ArrayList<>());
        }

        edgeCnt = new int[2*N+1];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String A = st.nextToken();
            String B = st.nextToken();
            setMap(A, B);
            adj.get(nameMap.get(A)).add(nameMap.get(B));
            edgeCnt[nameMap.get(B)]++;
        }

        solve();

        if (!check()) System.out.println(-1);
        else System.out.println(sb);
    }

    static boolean check() {
        for (int i = 0 ; i < idx ; i++) {
            if (edgeCnt[i] != 0) {
                return false;
            }
        }

        return true;
    }

    static void solve() {
        Queue<Integer> Q = new LinkedList<>();

        for (int i = 0 ; i < idx ; i++) {
            if (edgeCnt[i] == 0) Q.add(i);
        }

        while (!Q.isEmpty()) {
            int size = Q.size();
            List<String> list = new ArrayList<>();
            for (int i = 0 ; i < size ; i++) {
                int now = Q.poll();
                list.add(numMap.get(now));
                for (int next : adj.get(now)) {
                    edgeCnt[next]--;
                    if (edgeCnt[next] == 0) {
                        Q.add(next);
                    }
                }
            }

            Collections.sort(list);

            for (String s : list) {
                sb.append(s).append("\n");
            }
        }
    }

    static void setMap(String A, String B) {
        if (!nameMap.containsKey(A)) {
            nameMap.put(A, idx);
            numMap.put(idx, A);
            idx++;
        }

        if (!nameMap.containsKey(B)) {
            nameMap.put(B, idx);
            numMap.put(idx, B);
            idx++;
        }
    }
}