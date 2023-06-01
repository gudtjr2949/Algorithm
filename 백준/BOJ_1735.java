package coding_test.백준;

import java.io.*;

// 백준 1735 : 분수 합
public class BOJ_1735 {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s_1 = bf.readLine();
        String[] s_1_arr = s_1.split(" ");

        int A_1 = Integer.parseInt(s_1_arr[0]);
        int B_1 = Integer.parseInt(s_1_arr[1]);

        String s_2 = bf.readLine();
        String[] s_2_arr = s_2.split(" ");

        int A_2 = Integer.parseInt(s_2_arr[0]);
        int B_2 = Integer.parseInt(s_2_arr[1]);

        int tmp_A_1 = 1;
        int tmp_A_2 = 1;

        int tmp_B = 0;

        tmp_B = B_1 * B_2;

        tmp_A_1 = A_1 * (tmp_B / B_1);
        tmp_A_2 = A_2 * (tmp_B / B_2);

        int tmp_A_sum = tmp_A_1 + tmp_A_2;

        int y = 0;

        for (int i = 1 ; i <= Math.min(tmp_A_sum, tmp_B) ; i++) {
            if (tmp_A_sum % i == 0 && tmp_B % i == 0){
                y = i;
            }
        }


        System.out.println(tmp_A_sum/y + " " + tmp_B/y);


    }
}
