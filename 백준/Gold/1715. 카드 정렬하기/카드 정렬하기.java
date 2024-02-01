import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> PQ = new PriorityQueue<>();

        for (int i = 0 ; i < N ; i++) {
            PQ.add(Integer.parseInt(bf.readLine()));
        }

        long sum = 0;

        while (!PQ.isEmpty()) {
            int num1 = PQ.poll();
            if (PQ.isEmpty()) {
                break;
            }
            int num2 = PQ.poll();
            int tmpSum = num1 + num2;
            sum += tmpSum;
            PQ.add(tmpSum);
        }

        System.out.println(sum);
    }
}