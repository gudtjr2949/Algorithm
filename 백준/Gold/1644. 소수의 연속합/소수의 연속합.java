import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        solve();
        System.out.println(answer);
    }

    static void solve() {
        boolean[] prime = new boolean[N+1];

        prime[0] = prime[1] = true;

        for (int i = 2 ; i <= Math.sqrt(N) ; i++) {

            if (prime[i]) continue;

            for (int j = i*i ; j <= N ; j+=i) {
                prime[j] = true;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1 ; i <= N ; i++) {
            if (!prime[i]) list.add(i);
        }

        int[] prefix = new int[list.size()+1];

        for (int i = 1 ; i <= list.size() ; i++) {
            prefix[i] = list.get(i-1) + prefix[i-1];
        }

        twoPointer(prefix);
    }

    static void twoPointer(int[] prefix) {
        int left = 0;
        int right = 1;

        while (left < right && right < prefix.length && left < prefix.length) {
            int sum = prefix[right] - prefix[left];

            if (sum == N) answer++;

            if (sum < N) right++;
            else left++;
        }
    }
}