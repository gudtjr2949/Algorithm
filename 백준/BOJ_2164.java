package coding_test.백준;

import java.io.*;
import java.util.*;

// 백준 2164 : 카드 2
public class BOJ_2164 {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1 ; i <= N ; i++) {
            queue.add(i);
        }

        for (int i = 0 ; i < N-1 ; i++) {
            queue.remove();

            int tmp = queue.poll();

            queue.add(tmp);
        }

        System.out.println(queue.poll());
    }
}