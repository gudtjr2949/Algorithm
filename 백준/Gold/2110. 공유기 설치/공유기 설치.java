import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N, C, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr);

        binarySearch();

        System.out.println(answer);
    }

    static void binarySearch() {
        int left = 1;
        int right = arr[N-1] - arr[0] + 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (checkInstall(mid) < C) { // 공유기를 너무 적게 설치함. 다음 순서에는 간격을 더 좁혀서 촘촘하게 설치해야 함
                right = mid;
            } else { // 공유기를 너무 많이 설치함. 다음 순서에는 간격을 넓혀서 더 넓찍하게 설치해야 함
                left = mid + 1;
            }
        }

        // left 는 공유기를 설치할 수 있는 C를 가장 최초로 초과한 수
        answer = left - 1;
    }

    static int checkInstall(int distance) {
        int cnt = 1; // 첫번째 인덱스 공유기는 반드시 설치함
        int lastInstalled = 0; // 가장 최근에 설치한 공유기 인덱스

        for (int i = 1 ; i < N ; i++) {
            if (arr[i] - arr[lastInstalled] >= distance) {
                cnt++;
                lastInstalled = i;
            }
        }

        return cnt;
    }

}