import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static String num;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num = bf.readLine();

        Deque<Character> deque = new LinkedList<>();

        for (int i = 0 ; i < num.length() ; i++) {
            while (K > 0 && !deque.isEmpty()) {
                if (deque.peekLast() < num.charAt(i)) {
                    deque.pollLast();
                    K--;
                } else {
                    break;
                }
            }

            deque.add(num.charAt(i));
        }

        int size = deque.size();

        for (int i = size - K ; i < size ; i++) {
            deque.pollLast();
        }

        StringBuilder sb = new StringBuilder();

        size = deque.size();

        for (int i = 0 ; i < size ; i++) {
            sb.append(deque.pollFirst());
        }

        System.out.println(sb);
    }

}