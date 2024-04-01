import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> PQ = new PriorityQueue<>();

        for (int i = 0 ; i < N ; i++) {
            int X = Integer.parseInt(bf.readLine());

            if (X == 0) {
                if (PQ.size() == 0) sb.append(0).append("\n");
                else sb.append(PQ.poll()).append("\n");
            } else {
                PQ.add(X);
            }
        }

        System.out.println(sb);

    }
}