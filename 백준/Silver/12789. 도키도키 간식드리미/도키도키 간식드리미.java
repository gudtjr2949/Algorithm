import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static boolean possible = true;
    static Queue<Integer> Q;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        Q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            Q.add(Integer.parseInt(st.nextToken()));
        }

        solve();

        if (possible) System.out.println("Nice");
        else System.out.println("Sad");
    }

    static void solve() {
        int idx = 1;

        Stack<Integer> otherLine = new Stack<>();

        while (!Q.isEmpty()) {
            int tmp = Q.peek();
            if (tmp == idx) {
                idx++;
                Q.poll();
            } else {
                if (otherLine.size() > 0 && otherLine.peek() == idx) {
                    idx++;
                    otherLine.pop();
                } else {
                    Q.poll();
                    otherLine.add(tmp);
                }
            }
        }

        while (!otherLine.isEmpty()) {
            int tmp = otherLine.pop();
            if (tmp == idx) {
                idx++;
            } else {
                possible = false;
                return;
            }
        }
    }
}