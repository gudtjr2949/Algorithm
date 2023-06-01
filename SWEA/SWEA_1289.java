package coding_test.SWEA;

import java.io.*;

// SWEA 1289 : 원재의 메모리 복구하기
public class SWEA_1289 {
    static int[] arr;
    static int cnt;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            String s = bf.readLine();

            arr = new int[s.length()];

            for (int j = 0; j < s.length(); j++)
                arr[j] = s.charAt(j) - 48;

            cnt = 0;

            int n = 0;

            recursive(0, n);

            sb.append("#").append(i + 1).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static void recursive(int idx, int n) {
        // basis
        if (idx == arr.length) {
            return;
        }

        // logic
        if (arr[idx] != n) {
            n = arr[idx];
            cnt++;
        }

        // inductive
        recursive(idx+1, n);

    }
}


