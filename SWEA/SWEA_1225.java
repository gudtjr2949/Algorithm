package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// SWEA 1225 : 암호생성기
public class SWEA_1225 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int Test = Integer.parseInt(bf.readLine());

            Queue<Integer> queue = new LinkedList<>();

            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            for (int j = 0; j < 8; j++) {
                queue.add(Integer.parseInt(s_arr[j]));
            }

            int idx = 1;

            while (true) {
                int tmp = queue.poll();
                tmp -= idx;
                if (tmp <= 0) {
                    queue.add(0);
                    break;
                }
                queue.add(tmp);

                idx++;

                if (idx == 6)
                    idx = 1;
            }

            String answer = "";

            for (int j = 0; j < 8; j++) {
                answer += queue.poll() + " ";
            }

            sb.append("#").append(Test).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
