import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> PQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int sum = Math.abs(o1) - Math.abs(o2);
                if (sum == 0) {
                    return o1 - o2;
                } else {
                    return sum;
                }
            }
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < N ; i++) {
            int num = Integer.parseInt(bf.readLine());
            if (num == 0) {
                if (PQ.isEmpty()) sb.append(0).append("\n");
                else sb.append(PQ.poll()).append("\n");
            } else {
                PQ.add(num);
            }
        }

        System.out.println(sb);
    }
}