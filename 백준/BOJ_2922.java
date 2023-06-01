package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 2922 : 즐거운 단어
public class BOJ_2922 {

    static String s;
    static boolean[] alpha = new boolean[26];
    static long answer;
    static char[] arr = {'A', 'B', 'L'};

    /*
    순열에서 'A', 'B', 'L' 이 셋 중 하나만 선택 할 수 있게 하기
     */

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        s = bf.readLine();

        answer = 0;

        char[] mom = {'A', 'E', 'I', 'O', 'U'};

        for (int i = 0; i < 5; i++) {
            alpha[mom[i] - 'A'] = true;
        }

        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '_') {
                cnt++;
            }
        }

        solve(0, new char[cnt]);

        System.out.println(answer);
    }

    private static void solve(int idx, char[] input) {
        if (idx == input.length) {
            if (check(input)) {
                cnt(input);
            }
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            input[idx] = arr[i];
            solve(idx + 1, input);
        }

    }

    private static boolean check(char[] input) {
        char[] tmp = s.toCharArray();

        int tmp_idx = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '_') {
                tmp[i] = input[tmp_idx];
                tmp_idx++;
            }
        }

        int mom = 0;
        int son = 0;

        for (int i = 0; i < tmp.length; i++) {
            for (int j = i; j < i + 3; j++) {
                if (j >= tmp.length) {
                    break;
                }
                if (alpha[tmp[j] - 'A']) { // 모음
                    mom++;
                } else {
                    son++;
                }
            }

            if (mom == 3 || son == 3) {
                return false;
            } else {
                mom = 0;
                son = 0;
            }
        }

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == 'L') {
                return true;
            }
        }

        return false;
    }

    private static void cnt(char[] input) {
        long sum = 1;

        for (int i = 0; i < input.length; i++) {
            if (input[i] == 'A') {
                sum *= 5;
            } else if (input[i] == 'B') {
                sum *= 20;
            }
        }

        answer += sum;
    }
}