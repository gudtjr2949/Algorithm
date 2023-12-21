import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, S;
    static long answer;
    static long[] arr;
    static List<Long> L, R;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        L = new ArrayList<>();
        R = new ArrayList<>();

        arr = new long[N];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        findLeft(0, N/2, 0);
        findRight(N/2, N, 0);

        Collections.sort(L);
        Collections.sort(R);

        findAnswer();

        if (S == 0) {
            answer--;
        }

        System.out.println(answer);
    }

    private static void findLeft(int idx, int end, long sum) {
        if (idx == end) {
            L.add(sum);
            return;
        }

        findLeft(idx+1, end, sum + arr[idx]);
        findLeft(idx+1, end, sum);
    }

    private static void findRight(int idx, int end, long sum) {
        if (idx == end) {
            R.add(sum);
            return;
        }

        findRight(idx+1, end, sum + arr[idx]);
        findRight(idx+1, end, sum);
    }

    private static void findAnswer() {
        int left = 0;
        int right = R.size() - 1;

        while (left < L.size() && right >= 0) {
            long sum = L.get(left) + R.get(right);

            if (sum == S) {
                // 각각의 왼쪽 부분수열 배열과, 오른쪽 부분수열 배열에 똑같은 수가 있는지 찾아야 함
                long l = L.get(left);
                long cnt1 = 0;
                while (left < L.size() && L.get(left) == l) {
                    left++;
                    cnt1++;
                }

                long r = R.get(right);
                long cnt2 = 0;
                while (right >= 0 && R.get(right) == r) {
                    right--;
                    cnt2++;
                }

                answer += cnt1 * cnt2;
            }
            else if (sum > S) {
                right--;
            } else {
                left++;
            }
        }
    }
}