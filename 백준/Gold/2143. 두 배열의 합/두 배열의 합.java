import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, T;
    static long answer;
    static int[] A, B;
    static List<Long> prefixA, prefixB;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        A = new int[N];
        prefixA = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < N ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(bf.readLine());
        B = new int[M];
        prefixB = new ArrayList<>();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < M ; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        makePrefix();

        solve();

        System.out.println(answer);
    }

    static void solve() {
        int left = 0;
        int right = prefixB.size()-1;

        while (left < prefixA.size() && right >= 0) {
            long sum = prefixA.get(left) + prefixB.get(right);

            if (sum > T) right--;
            else if (sum < T) left++;
            else {
                long cntA = 0;
                long preA = prefixA.get(left);
                while (left < prefixA.size() && preA == prefixA.get(left)) {
                    cntA++;
                    left++;
                }

                long cntB = 0;
                long preB = prefixB.get(right);
                while (right >= 0 && preB == prefixB.get(right)) {
                    cntB++;
                    right--;
                }

                answer += cntA * cntB;
            }
        }
    }

    static void makePrefix() {
        for (int i = 0 ; i < N ; i++) {
            long sum = 0;
            for (int j = i ; j < N ; j++) {
                sum += A[j];
                prefixA.add(sum);
            }
        }

        for (int i = 0 ; i < M ; i++) {
            long sum = 0;
            for (int j = i ; j < M ; j++) {
                sum += B[j];
                prefixB.add(sum);
            }
        }

        Collections.sort(prefixA);
        Collections.sort(prefixB);
    }
}