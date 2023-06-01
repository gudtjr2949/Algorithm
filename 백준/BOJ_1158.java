package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 백준 1158 : 요세푸스 문제
public class BOJ_1158 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        int N = Integer.parseInt(s_arr[0]);
        int K = Integer.parseInt(s_arr[1]);

        ArrayList<Integer> list = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>(); // 처음 원

        for (int i = 0; i < N; i++) {
            queue.add(i + 1);
        }

        int cnt = 1; // 원을 도는 카운트

        while (!queue.isEmpty()) {
            if (cnt == K) {
                list.add(queue.poll());
                cnt = 1;
            }
            else {
                cnt++;
                int tmp = queue.poll();
                queue.add(tmp);
            }
        }

        print(list);
        System.out.println(sb);
    }

    private static void print(ArrayList<Integer> list) {
        sb.append("<");
        for (int i = 0 ; i < list.size() - 1 ; i++) {
            sb.append(list.get(i)).append(", ");
        }
        sb.append(list.get(list.size()-1)).append(">");
    }
}
