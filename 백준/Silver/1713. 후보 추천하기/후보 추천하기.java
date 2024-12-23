import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] answer;
    static List<Node> list;
    static Set<Integer> set;
    static class Node {
        int time, idx, cnt;

        public Node(int time, int idx, int cnt) {
            this.time = time;
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "time=" + time +
                    ", idx=" + idx +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        list = new ArrayList<>();
        set = new HashSet<>();

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < M ; i++) {
            solve(i, Integer.parseInt(st.nextToken()));
        }

        answer = new int[list.size()];
        for (int i = 0 ; i < list.size() ; i++) {
            answer[i] = list.get(i).idx;
        }

        Arrays.sort(answer);

        for (int i = 0 ; i < answer.length ; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    static void solve(int time, int idx) {
        // 사진틀에서 학생 한명 뺴야함
        if (!set.contains(idx) && list.size() >= N) {
            Collections.sort(list, (o1, o2) -> {
                if (o1.cnt == o2.cnt) return o1.time - o2.time;
                return o1.cnt - o2.cnt;
            });
            set.remove(list.get(0).idx);
            list.remove(0);
        }

        if (set.contains(idx)) {
            for (int i = 0 ; i < list.size() ; i++) {
                if (list.get(i).idx == idx) {
                    list.get(i).cnt++;
                    break;
                }
            }
        } else {
            set.add(idx);
            list.add(new Node(time, idx, 1));
        }

    }
}