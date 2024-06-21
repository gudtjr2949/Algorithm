import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int A, B, answer;
    static class Node {
        String num;
        int cnt;

        public Node(String num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.cnt - n2.cnt;
            }
        });

        PQ.add(new Node(Integer.toString(B), 0));

        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            int num = Integer.parseInt(now.num);

            if (num < A) {
                break;
            }

            if (num == A) {
                answer = now.cnt+1;
                return;
            }

            if (num % 2 == 0) {
                PQ.add(new Node(Integer.toString(num / 2), now.cnt+1));
            }
            if (num % 10 == 1) {
                PQ.add(new Node(Integer.toString(num / 10), now.cnt+1));
            }
        }

        answer = -1;
    }
}