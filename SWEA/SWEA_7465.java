package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 7465 : 창용 마을 무리의 개수
public class SWEA_7465 {

    static int N;
    static int M;
    static int[] set;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int Test = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < Test ; i++) {
            String nm = bf.readLine();
            String[] nm_arr = nm.split(" ");

            N = Integer.parseInt(nm_arr[0]);
            M = Integer.parseInt(nm_arr[1]);

            set = new int[N+1];

            for (int j = 1 ; j < N + 1 ; j++) {
                set[j] = j;
            }

            for (int j = 0 ; j < M ; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");

                int a = Integer.parseInt(s_arr[0]);
                int b = Integer.parseInt(s_arr[1]);

                union(a, b);
            }

            int cnt = 0;

            for (int j = 1 ; j < N + 1 ; j++) {
                if (set[j] == j){
                    cnt++;
                }
            }
            sb.append("#").append(i+1).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            set[pb] = pa;
        }
    }

    private static int find(int num) {
        if (set[num] == num) {
            return num;
        }
        else {
            return set[num] = find(set[num]);
        }
    }
}
