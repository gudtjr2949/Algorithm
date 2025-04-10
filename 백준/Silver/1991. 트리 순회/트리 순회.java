import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringBuilder sb;
    static Node[] tree;
    static class Node {
        char value;
        Node left, right;

        public Node(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(sb);
    }

    static void solve() {
        preOrder(tree[0]);
        sb.append("\n");

        inOrder(tree[0]);
        sb.append("\n");

        postOrder(tree[0]);
        sb.append("\n");
    }

    static void preOrder(Node now) {
        if (now == null) return;
        sb.append(now.value);
        preOrder(now.left);
        preOrder(now.right);
    }

    static void inOrder(Node now) {
        if (now == null) return;
        inOrder(now.left);
        sb.append(now.value);
        inOrder(now.right);
    }

    static void postOrder(Node now) {
        if (now == null) return;
        postOrder(now.left);
        postOrder(now.right);
        sb.append(now.value);
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (tree[parent-'A'] == null) {
                tree[parent-'A'] = new Node(parent); // 부모 노드 생성
            }
            if (left != '.') {
                tree[left-'A'] = new Node(left); // 왼쪽 자식 노드 생성
                tree[parent-'A'].left = tree[left-'A']; // 부모 노드와 왼쪽 자식 노드 연결
            }
            if (right != '.') {
                tree[right-'A'] = new Node(right); // 오른쪽 자식 노드 생성
                tree[parent-'A'].right = tree[right-'A']; // 부모 노드와 오른쪽 자식 노드 연결
            }
        }
    }

    static void init() {
        tree = new Node[N+1];
        sb = new StringBuilder();
    }
}