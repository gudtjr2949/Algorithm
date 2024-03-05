import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }

        public void insert(int input) {
            if (this.value < input) { // 입력값이 더 크다 -> right
                if (this.right == null) this.right = new Node(input);
                else this.right.insert(input);
            } else {
                if (this.left == null) this.left = new Node(input);
                else this.left.insert(input);
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(bf.readLine()));

        String input;
        while (true) {
            input = bf.readLine();
            if (input == null || input.equals(""))
                break;
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);
        System.out.println(sb);
    }

    static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append("\n");
    }
}