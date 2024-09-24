import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, answer = -1;
    static class Node {
        int front, back, cnt;

        public Node(int front, int back, int cnt) {
            this.front = front;
            this.back = back;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String s = bf.readLine();

        int backCnt = 0;
        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == 'T') backCnt++;
        }

        bfs(backCnt);

        System.out.println(answer);
    }

    static void bfs(int backCnt) {
        PriorityQueue<Node> Q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cnt - o2.cnt;
            }
        });

        boolean[] visited = new boolean[N+1];
        visited[backCnt] = true;
        Q.add(new Node(N-backCnt, backCnt, 0));

        while (!Q.isEmpty()) {
            Node now = Q.poll();
            int nowBack = now.back;
            int nowFront = now.front;

            if (nowBack == N && nowFront == 0) {
                answer = now.cnt;
                return;
            }

            for (int i = 0 ; i <= K ; i++) {
                int reverseBack = i; // 뒤집을 뒷면 코인 갯수
                int reverseFront = K-i; // 뒤집을 앞면 코인 갯수

                if (reverseBack <= nowBack && reverseFront <= nowFront) {
                    int nextBack = nowBack - reverseBack + reverseFront;
                    if (!visited[nextBack]) {
                        visited[nextBack] = true;
                        Q.add(new Node(N-nextBack, nextBack, now.cnt+1));
                    }
                }
            }
        }
    }
}