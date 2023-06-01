package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 1859 : 백만 장자 프로젝트
public class SWEA_1859 {

    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            N = Integer.parseInt(bf.readLine());
            arr = new int[N];

            int max = 0;
            int index = 0;

            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            for (int j = 0; j < N; j++)
                arr[j] = Integer.parseInt(s_arr[j]);

            Number findMaxIndex = find_max(arr, index);

            max = findMaxIndex.max;

            long sum = 0;

            for (int j = 0; j < N; j++) {
                if (arr[j] < max) {
                    sum += max - arr[j];
                } else if (arr[j] == max) {
                    index = j + 1;
                    findMaxIndex = find_max(arr, index);

                    max = findMaxIndex.max;
                }
            }

            sb.append("#").append(i+1).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);
    }

    static class Number {
        int max;
        int index;

        public Number(int max, int index) {
            this.max = max;
            this.index = index;
        }
    }

    private static Number find_max(int[] arr, int idx) {
        int max = 0;

        int index = 0;

        for (int i = idx; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }

        return new Number(max, index);
    }
}
