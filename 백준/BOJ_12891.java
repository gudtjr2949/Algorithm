package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 12891 : DNA 비밀번호
public class BOJ_12891 {

    static int P;
    static int S;
    static int cnt;
    static int a, c, g, t;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        String[] s_arr = s.split(" ");

        S = Integer.parseInt(s_arr[0]);
        P = Integer.parseInt(s_arr[1]);

        String arr = bf.readLine();

        // 부분문자열의 각 알파벳 수 담는 배열
        int[] alpha = new int[26];

        for (int i = 0 ; i < P ; i++) {
            alpha[arr.charAt(i) - 'A']++;
        }

        String s2 = bf.readLine();
        String[] s2_arr = s2.split(" ");

        a = Integer.parseInt(s2_arr[0]);
        c = Integer.parseInt(s2_arr[1]);
        g = Integer.parseInt(s2_arr[2]);
        t = Integer.parseInt(s2_arr[3]);

        solve(alpha);

        for (int i = 0 ; i < S - P ; i++) {
            // 젤 왼쪽 수 빼기
            alpha[arr.charAt(i) - 'A']--;

            // 젤 오른쪽 수 추가하기
            alpha[arr.charAt(i+P) - 'A']++;

            solve(alpha);
        }

        System.out.println(cnt);
    }

    private static void solve(int[] alpha) {
        // 비밀번호 문자열 최소 개수 지켰는지 확인
        if (alpha['A' - 'A'] - a >= 0 && alpha['C' - 'A'] - c >= 0 && alpha['G' - 'A'] - g >= 0 && alpha['T' - 'A'] - t >= 0) {
            cnt++;
        }
    }
}
