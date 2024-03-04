import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();
    static Node[] tree;
    static class Node {
        char value;
        Node left, right;

        public Node(char value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        tree = new Node[N+1];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            char parents = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (tree[parents - 'A'] == null) {
                tree[parents - 'A'] = new Node(parents);
            }

            if (left != '.') {
                tree[left - 'A'] = new Node(left);
                tree[parents - 'A'].left = tree[left - 'A'];
            }

            if (right != '.') {
                tree[right - 'A'] = new Node(right);
                tree[parents - 'A'].right = tree[right - 'A'];
            }
        }

        preOrder(tree[0]);
        sb.append("\n");
        inOrder(tree[0]);
        sb.append("\n");
        postOrder(tree[0]);

        System.out.println(sb);
    }

    static void preOrder(Node node) {
        if (node == null) return;
        sb.append(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    static void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        sb.append(node.value);
        inOrder(node.right);
    }

    static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value);
    }
}