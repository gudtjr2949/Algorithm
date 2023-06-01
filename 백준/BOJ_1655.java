package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// 백준 1655 : 가운데를 말해요
public class BOJ_1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());

        PriorityQueue<Integer> min = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> max = new PriorityQueue<>();

        for (int i = 0 ; i < N ; i++) {
            int number = Integer.parseInt(bf.readLine());

            if (max.size() < min.size()) {
                max.add(number);
            } else {
                min.add(number);
            }

            // 처음이 아닌 경우
            if (max.size() != 0) {
                if (min.peek() > max.peek()) {
                    int tmp = min.poll();
                    min.add(max.poll());
                    max.add(tmp);
                }
            }

            if (min.size() > max.size()) {
                sb.append(min.peek()).append("\n");
            } else {
                int num = Math.min(max.peek(), min.peek());
                sb.append(num).append("\n");
            }
        }

        System.out.println(sb);
    }
}
