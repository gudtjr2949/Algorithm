import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] edgeCnt;
    static Map<String, Integer> name = new HashMap<>();
    static Map<Integer, String> num = new HashMap<>();
    static List<List<Integer>> adj = new ArrayList<>();
    static List<List<String>> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String[] sArr = new String[N];

        for (int i = 0 ; i < N ; i++) {
            sArr[i] = st.nextToken();
        }

        Arrays.sort(sArr);

        for (int i = 1 ; i <= N ; i++) {
            name.put(sArr[i-1], i);
            num.put(i, sArr[i-1]);
        }

        edgeCnt = new int[N+1];

        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
            answer.add(new ArrayList<>());
        }

        int M = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int down = name.get(st.nextToken());
            int up = name.get(st.nextToken());
            adj.get(up).add(down);
            edgeCnt[down]++;
        }

        solve();
    }

    static void solve() {
        Queue<Integer> Q = new LinkedList<>();
        List<String> root = new ArrayList<>();

        for (int i = 1 ; i <= N ; i++) {
            if (edgeCnt[i] == 0) {
                Q.add(i);
                root.add(num.get(i));
            }
        }

        while (!Q.isEmpty()) {
            int now = Q.poll();

            for (int child : adj.get(now)) {
                edgeCnt[child]--;
                if (edgeCnt[child] == 0) {
                    answer.get(now).add(num.get(child));
                    Q.add(child);
                }
            }
        }

        Collections.sort(root);

        System.out.println(root.size());
        for (String s : root) {
            System.out.print(s + " ");
        }
        System.out.println();

        for (int i = 1 ; i <= N ; i++) {
            System.out.print(num.get(i) + " " + answer.get(i).size() + " ");
            Collections.sort(answer.get(i));
            for (String child : answer.get(i)) {
                System.out.print(child + " ");
            }
            System.out.println();
        }
    }
}