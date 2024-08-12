import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, cnt;
    static int[] edgeCount;
    static List<Integer> answerList;
    static List<List<Node>> adj;
    static class Node {
        int student1, student2;

        public Node(int student1, int student2) {
            this.student1 = student1;
            this.student2 = student2;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        edgeCount = new int[N+1];

        adj = new ArrayList<>();
        for (int i = 0 ; i <= N ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int student1 = Integer.parseInt(st.nextToken());
            int student2 = Integer.parseInt(st.nextToken());
            adj.get(i).add(new Node(student1, student2));
            edgeCount[student1]++;
            edgeCount[student2]++;
        }

        solve();

        System.out.println(cnt);
        for (Integer num : answerList) {
            System.out.print(num + " ");
        }
    }

    static void solve() {
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        for (int i = 1 ; i <= N ; i++) {
            if (edgeCount[i] < 2) Q.add(i);
        }

        while (!Q.isEmpty()) {
            int now = Q.poll();

            if (visited[now]) continue;
            visited[now] = true;

            for (Node next : adj.get(now)) {
                if (--edgeCount[next.student1] < 2) Q.add(next.student1);
                if (--edgeCount[next.student2] < 2) Q.add(next.student2);
            }
        }

        cnt = 0;
        answerList = new ArrayList<>();

        for (int i = 1 ; i <= N ; i++) {
            if (edgeCount[i] >= 2) {
                cnt++;
                answerList.add(i);
            }
        }

    }
}