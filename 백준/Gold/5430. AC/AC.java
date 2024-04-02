import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int Test = 0 ; Test < T ; Test++) {
            String order = bf.readLine();
            int N = Integer.parseInt(bf.readLine());
            Deque<Integer> deque = new LinkedList<>();

            String s1 = bf.readLine();
            String s2 = s1.replace("[", "");
            String s3 = s2.replace("]", "");

            StringTokenizer st = new StringTokenizer(s3, ",");

            for (int i = 0 ; i < N ; i++)
                deque.add(Integer.parseInt(st.nextToken()));

            int dir = 0; // dir = 0 이면 정방향, dir = 1 이면 역방향

            String s = "[";

            for (int i = 0 ; i < order.length() ; i++) {
                if (order.charAt(i) == 'R') {
                    if (dir == 0) dir = 1;
                    else dir = 0;
                } else {
                    if (deque.size() == 0) {
                        s = "error";
                        break;
                    } else if (dir == 0) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                }
            }

            if (!s.equals("error")) {
                s = makePrint(dir, deque);
            }

            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }

    static String makePrint(int dir, Deque<Integer> deque) {
        StringBuilder s = new StringBuilder("[");

        if (dir == 0) {
            while (!deque.isEmpty()) {
                s.append(deque.pollFirst()).append(",");
            }
        } else {
            while (!deque.isEmpty()) {
                s.append(deque.pollLast()).append(",");
            }
        }

        if (s.length() > 1) {
            s.deleteCharAt(s.length() - 1);
        }

        s.append("]");

        return s.toString();
    }
}