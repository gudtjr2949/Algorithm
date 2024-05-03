import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[][] arr;
    static int[] dx = {1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());

            arr = new char[2][N];

            String s = bf.readLine();
            for (int i = 0 ; i < N ; i++) {
                arr[0][i] = s.charAt(i);
            }

            s = bf.readLine();
            for (int i = 0 ; i < N ; i++) {
                arr[1][i] = s.charAt(i);
            }

            sb.append(solve()).append("\n");
        }

        System.out.println(sb);
    }

    static int solve() {
        // 확정 지뢰 찾기 *
        int cnt = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[1][i] == '*') cnt++;
        }

        for (int i = 0 ; i < N ; i++) {
            int tmp = 0;

            // 지뢰 설치 필요한 갯수 확인
            if (arr[0][i] - '0' > 0 && arr[0][i] - '0' <= 9)
                tmp = arr[0][i] - '0';
            else continue;

            // 주변에 확정 지뢰 있는지 확인
            for (int j = 0 ; j < 3 ; j++) {
                int nx = i + dx[j];

                if (nx >= 0 && nx < N && arr[1][nx] == '*') tmp--;
            }

            // '오른쪽 -> 나 -> 왼쪽' 순서대로 지뢰 설치
            for (int j = 0 ; j < 3 ; j++) {
                int nx = i + dx[j];

                if (tmp > 0 && nx >= 0 && nx < N && arr[1][nx] == '#') {
                    arr[1][nx] = '*';
                    cnt++;
                    tmp--;
                }
            }
        }

        return cnt;
    }
}