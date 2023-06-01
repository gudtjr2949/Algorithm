package coding_test.백준;

import java.io.*;
import java.util.*;

// 백준 16206 : 롤케이크
public class BOJ_16206 {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        String[] s_arr = s.split(" ");
        int N = Integer.parseInt(s_arr[0]);
        int M = Integer.parseInt(s_arr[1]);

        String a = bf.readLine();
        String[] a_arr = a.split(" ");

        // 10의 배수
        int[] arr = new int[N];

        // 10의 배수가 아닌 거
        int[] tmp = new int[N];

        int arr_idx = 0;

        int tmp_idx = 0;

        for (int i = 0 ; i < N ; i++){
            if (Integer.parseInt(a_arr[i]) % 10 == 0){
                arr[arr_idx] = Integer.parseInt(a_arr[i]);
                arr_idx++;
            }
            else {
                tmp[tmp_idx] = Integer.parseInt(a_arr[i]);
                tmp_idx++;
            }
        }

        Arrays.sort(arr);

        // 자른 수
        int cut = 0;

        // 케이크 수
        int cake = 0;

        // 일단 10의 배수인 것 부터 자르기
        for (int i = 0 ; i < arr.length ; i++){
            while (cut < M){
                if (arr[i] > 10) { // 10보다 커서 케이크를 한번 자름
                    arr[i] -= 10; // 일단 케이크를 10만큼 자르고
                    cut++; // 한번 잘랐으니까 cut 추가
                    cake++; // 그리고 케이크 하나 생겼으니까 추가
                }
                else if (arr[i] == 10) { // 해당 케이크는 자를 필요 없음
                    cake++;
                    arr[i] -= 10;
                    break;
                }
                else if (arr[i] < 10) { // 이 케이크는 이제 못 자르는 케이크
                    break;
                }
            }

            if (arr[i] == 10)
                cake++;
        }

        // 10의 배수가 아닌 케이크 자르기
        for (int i = 0 ; i < tmp.length ; i++){
            while (cut < M) {
                if (tmp[i] > 10) { // 10보다 커서 케이크를 한번 자름
                    tmp[i] -= 10; // 일단 케이크를 10만큼 자르고
                    cut++; // 한번 잘랐으니까 cut 추가
                    cake++; // 그리고 케이크 하나 생겼으니까 추가
                }
                else if (tmp[i] == 10) { // 해당 케이크는 자를 필요 없음
                    cake++;
                    tmp[i] -= 10;
                    break;
                }
                else if (tmp[i] < 10) { // 이 케이크는 이제 못 자르는 케이크
                    break;
                }
            }

            if (tmp[i] == 10)
                cake++;
        }

        System.out.println(cake);
    }
}
