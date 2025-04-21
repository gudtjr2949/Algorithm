import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int treeCnt;
    static StringBuilder sb = new StringBuilder();
    static Map<String, Integer> map = new HashMap<>();
    static List<Node> list = new ArrayList<>();
    static class Node {
        String s, percent;

        public Node(String s, String percent) {
            this.s = s;
            this.percent = percent;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {

        for(Map.Entry<String, Integer> m : map.entrySet()) {
            double percent = (m.getValue() / (double) treeCnt) * 100.0;
            list.add(new Node(m.getKey(), String.format("%.4f", percent)));
        }

        Collections.sort(list, (o1, o2) -> o1.s.compareTo(o2.s));

        for (Node tree : list) {
            sb.append(tree.s).append(" ").append(tree.percent).append("\n");
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = bf.readLine();
            if (s == null || s.isBlank()) {
                break;
            }

            if (map.containsKey(s)) {
                map.put(s, map.get(s)+1);
            } else {
                map.put(s, 1);
            }

            treeCnt++;
        }
    }
}