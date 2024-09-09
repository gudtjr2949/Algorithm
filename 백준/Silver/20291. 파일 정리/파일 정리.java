import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static StringBuilder sb;
    static List<Node> nodes;
    static Map<String, Integer> map;
    static class Node {
        String file;
        int cnt;

        public Node(String file, int cnt) {
            this.file = file;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        nodes = new ArrayList<>();
        map = new HashMap<>();
        sb = new StringBuilder();

        int idx = 0;

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), ".");
            st.nextToken();
            String file = st.nextToken();

            if (!map.containsKey(file)) {
                map.put(file, idx);
                nodes.add(new Node(file, 1));
                idx++;
            } else {
                nodes.get(map.get(file)).cnt++;
            }
        }

        solve();

        System.out.println(sb);
    }

    static void solve() {
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.file.compareTo(o2.file);
            }
        });

        for (Node node : nodes) {
            sb.append(node.file).append(" ").append(node.cnt).append("\n");
        }
    }
}