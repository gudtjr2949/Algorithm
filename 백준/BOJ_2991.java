package coding_test.백준;

import java.io.*;

// 백준 2991 : 사나운 개
public class BOJ_2991 {
    /*
    * 조건 1 : A개가 짖을 때 arr cnt++
    * 조건 2 : B개가 짖을 때 arr cnt++
    * 조건 3 : 쉴 때는 cnt 안함
    * 조건 4 : 정답은 각각 arr[도착시간-1]*/

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        String[] s_arr = s.split(" ");

        int A = Integer.parseInt(s_arr[0]);
        int B = Integer.parseInt(s_arr[1]);
        int C = Integer.parseInt(s_arr[2]);
        int D = Integer.parseInt(s_arr[3]);

        String c = bf.readLine();
        String[] c_arr = c.split(" ");

        int P = Integer.parseInt(c_arr[0]);
        int M = Integer.parseInt(c_arr[1]);
        int N = Integer.parseInt(c_arr[2]);

        int max = 0;

        for (int i = 0 ; i < 3 ; i++){
            max = Math.max(max, Integer.parseInt(c_arr[i]));
        }

        int[] arr = new int[max];

        boolean bark = true; // 짖으면 true, 안짖으면 false

        int A_cnt = 0;
        int B_cnt = 0;
        int C_cnt = 0;
        int D_cnt = 0;

        // A개
        for (int i = 0 ; i < max ; i++){
            if (A == A_cnt) { // 다 짖고 쉴 때 됐으면
                bark = false;
                A_cnt = 0;
            }

            if (B == B_cnt) { // 다 쉬고 짖을 때 됐으면
                bark = true;
                B_cnt = 0;
            }

            if (bark) { // 조건 1
                A_cnt++;
                arr[i]++;
            }
            else {
                B_cnt++;
            }
        }

        bark = true;

        // B개
        for (int i = 0 ; i < max ; i++){
            if (C == C_cnt) { // 다 짖고 쉴 때 됐으면
                bark = false;
                C_cnt = 0;
            }

            if (D == D_cnt) { // 다 쉬고 짖을 때 됐으면
                bark = true;
                D_cnt = 0;
            }

            if (bark) { // 조건 2
                C_cnt++;
                arr[i]++;
            }
            else {
                D_cnt++;
            }
        }

        // 조건 3
        System.out.println(arr[P-1]);
        System.out.println(arr[M-1]);
        System.out.println(arr[N-1]);
    }
}
