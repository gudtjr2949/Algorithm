import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long ATK, answer;
    static Node[] nodes;
    static class Node {
        long t, a, h;
        public Node(long t, long a, long h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        long left = 0;
        long right = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (findMaxHP(mid)) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        answer = left;
    }

    static boolean findMaxHP(long mid) {
        long nowHP = mid;
        long nowATK = ATK;

        Node[] tmp = copyNode();

        for (Node node : tmp) {

            if (node.t == 1) {
                if (node.h % nowATK == 0) nowHP -= (node.h / nowATK-1) * node.a;
                else nowHP -= (node.h / nowATK) * node.a;

                if (nowHP <= 0) return false;
            } else {
                nowHP += node.h;
                if (nowHP > mid) nowHP = mid;
                nowATK += node.a;
            }
        }

        return true;
    }

    static Node[] copyNode() {
        Node[] copied = new Node[N];
        for (int i = 0 ; i < N ; i++) copied[i] = new Node(nodes[i].t, nodes[i].a, nodes[i].h);
        return copied;
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        ATK = Long.parseLong(st.nextToken());

        init();

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            long t = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            nodes[i] = new Node(t, a, h);
        }
    }

    static void init() {
        nodes = new Node[N];
    }
}