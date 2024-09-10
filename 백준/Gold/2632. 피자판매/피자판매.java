import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int T, M, N, answer;
    static int[] A, B;
    static List<Integer> prefixA, prefixB;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        A = new int[M];
        B = new int[N];

        for (int i = 0 ; i < M ; i++) {
            A[i] = Integer.parseInt(bf.readLine());
        }

        for (int i = 0 ; i < N ; i++) {
            B[i] = Integer.parseInt(bf.readLine());
        }

        prefixA = new ArrayList<>();
        prefixA.add(0);
        prefixA = getPrefix(A);

        prefixB = new ArrayList<>();
        prefixB.add(0);
        prefixB = getPrefix(B);

        Collections.sort(prefixA);
        Collections.sort(prefixB, Collections.reverseOrder());

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = 0;

        while (left < prefixA.size() && right < prefixB.size()) {
            int sum = prefixA.get(left) + prefixB.get(right);

            if (sum == T) {
                int cntA = 0;
                int cntB = 0;
                int preA = prefixA.get(left);
                int preB = prefixB.get(right);

                while (left < prefixA.size() && prefixA.get(left) == preA) {
                    left++;
                    cntA++;
                }

                while (right < prefixB.size() && prefixB.get(right) == preB) {
                    right++;
                    cntB++;
                }

                answer += cntA * cntB;

            } else if (sum < T) {
                left++;
            } else {
                right++;
            }
        }

        for (Integer num : prefixA) {
            if (num == T) answer++;
        }

        for (Integer num : prefixB) {
            if (num == T) answer++;
        }
    }

    static List<Integer> getPrefix(int[] arr) {
        int n = arr.length;

        List<Integer> prefix = new ArrayList<>();

        int sum = 0;
        for (int i = 0 ; i < n ; i++) {
            sum = 0;
            for (int j = i ; j < i+n-1 ; j++) {
                sum += arr[j % n];
                prefix.add(sum);
            }
        }

        sum = 0;
        for (int i = 0 ; i < n ; i++) sum += arr[i];

        prefix.add(sum);

        return prefix;
    }
}