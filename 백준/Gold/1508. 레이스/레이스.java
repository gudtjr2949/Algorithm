import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0 ; i < K ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 모든 심판은 최대 distance 만큼 떨어져 있어야 함
        // 2. 가장 가깝게 배치된 심판의 거리가 최소 distance 임
        int distance = binarySearch();

        StringBuilder sb = new StringBuilder();

        sb.append("1"); // 처음 위치엔 반드시 심판이 배치되어야 함

        int lastIdx = 0; // 가장 최근에 배치한 심판의 인덱스
        int cnt = 1; // 현재 배치한 심판의 수

        for (int i = 1 ; i < K ; i++) {
            // 가장 최근에 배치한 심판과 현재 i 인덱스의 심판 사이의 거리가 distance 보다 크거나 같은 경우 -> 설치 가능
            // 현재까지 배치한 심판의 수가 M보다 작은 경우 -> 설치 가능
            if ((arr[i] - arr[lastIdx] >= distance) && (cnt < M)) {
                sb.append("1");
                lastIdx = i;
                cnt++;
            } else {
                sb.append("0");
            }
        }

        System.out.println(sb);
    }

    static int binarySearch() {
        int left = 1; // 배치할 수 있는 심판 사이 최소 거리 (최소 거리가 0은 존재 X)
        int right = arr[K-1] - arr[0] + 1; // 배치할 수 있는 심판 사이 최대 거리 (+ 1 을 해주는 이유는 심판 인덱스의 최소값이 0이기 때문)

        while (left < right) {
            int mid = (left + right) / 2; // 배치할 각 심판 사이의 최소 거리

            int cnt = findCnt(mid);
            if (cnt < M) { // 너무 심판을 적게 배치함 -> 심판 사이의 간격을 좁혀서 심판을 더 많이 배치해야 함 -> mid 가 작아져야 함
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left-1;
    }

    // 각 심판 사이의 최소 거리가 distance 일 때, 배치할 수 있는 심판 수
    static int findCnt(int distance) {
        int cnt = 1;
        int lastIdx = 0; // 가장 최근에 배치한 심판의 인덱스

        for (int i = 1 ; i < K ; i++) {
            if (arr[i] - arr[lastIdx] >= distance) {
                lastIdx = i;
                cnt++;
            }
        }

        return cnt;
    }
}