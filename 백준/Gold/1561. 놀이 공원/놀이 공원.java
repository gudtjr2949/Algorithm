import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long N, M;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        arr = new long[(int) M + 1];

        st = new StringTokenizer(bf.readLine());

        for (int i = 1 ; i <= M ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        if (N <= M) {
            System.out.println(N);
        } else {
            long minute = binarySearch(); // N번쨰 어린이가 놀이기구에 탑승한 시간

            long cnt = findBeforeOneMinuteCount(minute); // N번쨰 어린이가 놀이기구에 탑승한 시간 1분 전에 탑승한 모든 어린이 수

            System.out.println(findAnswer(cnt, minute));
        }
    }

    static long findAnswer(long cnt, long minute) {
        long answer = 0L;

        for (int i = 1 ; i <= M ; i++) {
            if (minute % arr[i] == 0) {
                cnt++;

                if (cnt == N) {
                    answer = i;
                    break;
                }
            }
        }

        return answer;
    }

    static long findBeforeOneMinuteCount(long minute) {
        long cnt = 0L;

        for (int i = 1 ; i <= M ; i++) {
            cnt += ((minute - 1) / arr[i]) + 1;
        }

        return cnt;
    }

    static long binarySearch() {
        long left = 0L;
        long right = 60000000000L;

        long result = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = 0L; // mid 분일 때 놀이기구에 탑승한 모든 어린이 수

            for (int i = 1 ; i <= M ; i++) {
                cnt += (mid / arr[i]) + 1;
            }

            if (cnt < N) {
                left = mid + 1;
            } else {
                right = mid - 1;
                result = Math.min(result, mid);
            }
        }

        return result;
    }
}