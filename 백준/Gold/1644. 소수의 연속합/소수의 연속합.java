import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N, answer;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        input();
        makePrimeList();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = 0;
        if (list.size() == 0) return;
        
        int sum = list.get(0);

        while (left < list.size() && right < list.size()) {
            if (sum < N) {
                if (right+1 == list.size()) break;
                right++;
                sum += list.get(right);
            } else {
                if (sum == N) answer++;

                sum -= list.get(left);
                left++;
            }
        }
    }

    static void makePrimeList() {
        boolean[] prime = new boolean[N+1];

        for (int i = 2 ; i <= Math.sqrt(N) ; i++) {
            if (prime[i]) continue;

            for (int j = i*i ; j <= N ; j+=i) { // 소수가 아닌 수를 지움
                prime[j] = true;
            }
        }

        for (int i = 2 ; i <= N ; i++) {
            if (!prime[i]) list.add(i);
        }
    }

    static void input() throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        init();
    }

    static void init() {
        list = new ArrayList<>();
    }
}