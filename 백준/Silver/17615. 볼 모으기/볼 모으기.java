import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, answer = 100_000_000;
    static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        arr = new char[N];
        String s = bf.readLine();

        for (int i = 0 ; i < N ; i++) {
            arr[i] = s.charAt(i);
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {

        int redCnt = 0;
        int blueCnt = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] == 'R') {
                redCnt++;
            } else {
                blueCnt++;
            }
        }

        int tmpCnt = 0;

        // 왼쪽에 파란공 모으기
        for (int i = 0 ; i < N ; i++) {
            if (arr[i] == 'B') {
                tmpCnt++;
            } else {
                break;
            }
        }

        answer = Math.min(answer, blueCnt-tmpCnt);

        tmpCnt = 0;

        // 왼쪽에 빨간공 모으기
        for (int i = 0 ; i < N ; i++) {
            if (arr[i] == 'R') {
                tmpCnt++;
            } else {
                break;
            }
        }

        answer = Math.min(answer, redCnt-tmpCnt);

        tmpCnt = 0;

        // 오른쪽에 파란공 모으기
        for (int i = N-1 ; i >= 0 ; i--) {
            if (arr[i] == 'B') {
                tmpCnt++;
            } else {
                break;
            }
        }

        answer = Math.min(answer, blueCnt-tmpCnt);

        tmpCnt = 0;

        // 오른쪽에 빨간공 모으기
        for (int i = N-1 ; i >= 0 ; i--) {
            if (arr[i] == 'R') {
                tmpCnt++;
            } else {
                break;
            }
        }

        answer = Math.min(answer, redCnt-tmpCnt);
    }
}