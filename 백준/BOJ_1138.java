package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 1138 : 한 줄로 서기
public class BOJ_1138 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];
        int[] output = new int[N];

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(s_arr[i]);
        }

        for (int i = 0 ; i < N ; i++) {
            int left = arr[i]; // i+1의 왼쪽에 있는 i+1의 키보다 큰 사람 수

            for (int j = 0 ; j < N ; j++) {
                if (left == 0 && output[j] == 0) {
                    output[j] = i + 1;
                    break;
                }
                else if (output[j] == 0){
                    left--;
                }
            }
        }

        for (int i = 0 ; i < N ; i++)
            System.out.print(output[i] + " ");
    }
}
