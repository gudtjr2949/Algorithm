import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        Queue<Integer> PQ1 = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> PQ2 = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(bf.readLine());

            if (PQ1.size() == PQ2.size()) PQ1.add(num);
            else PQ2.add(num);

            if (!PQ2.isEmpty() && PQ1.peek() > PQ2.peek()) {
                int tmp1 = PQ1.poll();
                int tmp2 = PQ2.poll();

                PQ1.add(tmp2);
                PQ2.add(tmp1);
            }

            sb.append(PQ1.peek()).append("\n");
        }

        System.out.println(sb);
    }
}