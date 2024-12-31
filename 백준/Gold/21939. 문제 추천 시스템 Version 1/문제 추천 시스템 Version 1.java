import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] levels;
    static Set<String> set;
    static Queue<Node> high, low;
    static StringBuilder sb = new StringBuilder();
    static class Node {
        int num, level;
        public Node(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        high = new PriorityQueue<>((o1, o2) -> {
            if (o1.level == o2.level) return o2.num - o1.num;
            return o2.level - o1.level;
        });

        low = new PriorityQueue<>((o1, o2) -> {
            if (o1.level == o2.level) return o1.num - o2.num;
            return o1.level - o2.level;
        });

        set = new HashSet<>();

        N = Integer.parseInt(bf.readLine());

        levels = new int[100_001];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            levels[P] = L;
            high.add(new Node(P, L));
            low.add(new Node(P, L));
        }

        M = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            solve(bf.readLine());
        }

        System.out.println(sb);
    }

    static void solve(String s) {
        StringTokenizer st = new StringTokenizer(s);

        String operation = st.nextToken();

        if (operation.equals("recommend")) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 1) {
                String key = high.peek().num + "-" + high.peek().level;
                while (set.contains(key)) {
                    high.poll();
                    key = high.peek().num + "-" + high.peek().level;
                }
                sb.append(high.peek().num).append("\n");
            } else {
                String key = low.peek().num + "-" + low.peek().level;
                while (set.contains(key)) {
                    low.poll();
                    key = low.peek().num + "-" + low.peek().level;
                }
                sb.append(low.peek().num).append("\n");
            }
        } else if (operation.equals("add")) {
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            levels[P] = L;
            high.add(new Node(P, L));
            low.add(new Node(P, L));
        } else {
            int P = Integer.parseInt(st.nextToken());
            String key = P + "-" + levels[P];
            set.add(key);
        }
    }

}