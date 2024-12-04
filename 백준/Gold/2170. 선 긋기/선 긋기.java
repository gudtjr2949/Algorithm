import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long answer;
    static long[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new long[N][2];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) return (int) (o1[1] - o2[1]);
            return (int) (o1[0] - o2[0]);
        });

        solve();

        System.out.println(answer);
    }

    static void solve() {
        long min = arr[0][0];//이전 선의 x좌표
        long max = arr[0][1];//이전 선의 y좌표

        answer = max - min;

        for(int i = 1 ; i < N ; i++) {
            if (min <= arr[i][0] && arr[i][1] <= max) { //현재 선이 이전 선에 포함된다면
                continue;
            } else if (arr[i][0] < max) { //현재 선의 시작점이 이전 선에 포함된다면
                answer += arr[i][1] - max;
            } else { //현재 선과 이전 선이 겹치지 않는다면
                answer += arr[i][1] - arr[i][0];
            }

            min = arr[i][0];
            max = arr[i][1];
        }
    }
}