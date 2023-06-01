package coding_test.백준;

import java.io.*;

// 백준 2941 : 크로아티아 알파벳
public class BOJ_2941 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = {"c=", "c-", "d-", "lj", "nj", "s=", "z="};

        String s = bf.readLine();

        char[] s_arr = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            s_arr[i] = s.charAt(i);
        }

        int answer = 0;

        // dz= 우선으로 검사
        for (int j = 0 ; j < s.length()-2 ; j++) {
            String tmp = "";
            tmp += s_arr[j];
            tmp += s_arr[j+1];
            tmp += s_arr[j+2];

            if (tmp.equals("dz=")){
//                System.out.println(tmp);
                answer++;
                s_arr[j] = ' ';
                s_arr[j+1] = ' ';
                s_arr[j+2] = ' ';
            }
        }


        // 2개씩
        for (int i = 0; i < 7; i++) {
            for (int j = 0 ; j < s.length()-1 ; j++) {
                String tmp = "";
                tmp += s_arr[j];
                tmp += s_arr[j+1];

                if (tmp.equals(arr[i])){
//                    System.out.println(tmp);
                    answer++;
                    s_arr[j] = ' ';
                    s_arr[j+1] = ' ';
                }
            }
        }

        // 빈 칸 체크
        for (int i = 0 ; i < s.length() ; i++) {
            if (s_arr[i] != ' ')
                answer++;
        }

        System.out.println(answer);
    }
}
