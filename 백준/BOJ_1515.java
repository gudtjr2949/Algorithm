package coding_test.백준;

import java.io.*;

// 백준 1515 : 수 이어 쓰기
public class BOJ_1515 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        int N = 1;

        int s_idx = 0;
        int tmp_idx = 0;

        while(true) {
            String tmp = N + "";

            if (s_idx < s.length() && tmp_idx < tmp.length()) {
                if (s.charAt(s_idx) == tmp.charAt(tmp_idx)) { // 같은 거 발견
                    s_idx++;
                }
                tmp_idx++;
            }
            else if (s_idx >= s.length()){
                break;
            }
            else {
                N++;
                tmp_idx = 0;
            }
        }

        System.out.println(N);
    }
}
