import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[] parents;
    static Node[] nodes;
    static class Node {
        int a, b, cost;
        public Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        nodes = new Node[M];

        for (int i = 1 ; i <= N ; i++) parents[i] = i;

        for (int i = 0 ; i < M ; i++) {
           st = new StringTokenizer(bf.readLine());
           int a = Integer.parseInt(st.nextToken());
           int b = Integer.parseInt(st.nextToken());
           int cost = Integer.parseInt(st.nextToken());
           nodes[i] = new Node(a, b, cost);
        }

        Arrays.sort(nodes, (o1, o2) -> o1.cost - o2.cost);

        int max = 0;

        for (Node node : nodes) {
            if (find(node.a) != find(node.b)) {
                answer += node.cost;
                max = Math.max(max, node.cost);
                union(node.a, node.b);
            }
        }

        System.out.println(answer - max);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) parents[b] = a;
        else parents[a] = b;
    }

    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }
}