import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer = 1;
    static Node[] lecture;
    static class Node {
        int idx, start, end;

        public Node(int idx, int start, int end) {
            this.idx = idx;
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        lecture = new Node[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int idx = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lecture[i] = new Node(idx, start, end);
        }

        Arrays.sort(lecture, (o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Queue<Integer> PQ = new PriorityQueue<>();

        for (int i = 0 ; i < N ; i++) {
            while (!PQ.isEmpty() && PQ.peek() <= lecture[i].start) {
                PQ.poll();
            }

            PQ.add(lecture[i].end);

            answer = Math.max(answer, PQ.size());
        }
    }
}