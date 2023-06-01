package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 14888 : 연산자 끼워넣기
public class BOJ_14888 {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N];

        int[] output = new int[N-1];

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        for (int i = 0 ; i < N ; i++)
            arr[i] = Integer.parseInt(s_arr[i]);

        int[] operator = new int[4];

        String a = bf.readLine();
        String[] a_arr = a.split(" ");

       for (int i = 0 ; i < 4 ; i++){
           operator[i] = Integer.parseInt(a_arr[i]);
       }

       perm(arr, output, operator, 0, 4, N-1); // operator 에서 연산자 4개중 연사자의 총 갯수 N-1개 뽑기

        System.out.println(max);
        System.out.println(min);
    }

    private static void perm(int[] arr, int[] output, int[] operator, int depth, int n, int r){
        if (depth == r) { // 끝까지 다 탐색했으면
            // 계산 로직
            int sum = arr[0];
            for (int i = 0 ; i < output.length ; i++) {
                if (output[i] == 1) { // +
                    sum = sum + arr[i+1];
                }
                else if (output[i] == 2) { // -
                    sum = sum - arr[i+1];
                }
                else if (output[i] == 3) { // *
                    sum = sum * arr[i+1];
                }
                else { // /
                    sum = sum / arr[i+1];
                }
            }

            max = Math.max(max , sum);
            min = Math.min(min, sum);
        }

        for (int i = 0 ; i < n ; i++) {
            if (operator[i] != 0) { // 해당 연산자를 사용할 수 있음
                operator[i]--;
                output[depth] = i+1;
                perm(arr, output, operator, depth+1, n, r);
                operator[i]++;
            }
        }
    }
}
