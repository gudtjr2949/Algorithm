package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 3289 : 서로소 집합
public class SWEA_3289 {

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

            StringBuilder answer = new StringBuilder();

            for (int j = 1 ; j < N+1 ; j++) {
                set[j] = j;
            }

            for (int j = 0 ; j < M ; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");

                int a = Integer.parseInt(s_arr[1]);
                int b = Integer.parseInt(s_arr[2]);

                if (Integer.parseInt(s_arr[0]) == 0) {
                    union(a, b);
                }
                else {
                    if (check(a, b)) {
                        answer.append("1");
                    }
                    else {
                        answer.append("0");
                    }
                }
            }

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static boolean check(int a, int b) {
        if (find(a) == find(b)) {
            return true;
        }
        else {
            return false;
        }
    }

    private static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            set[pb] = pa;
        }
    }

    // 해당 수의 대표값 찾기
    private static int find(int num) {
        if (set[num] == num) {
            return num;
        }
        else {
            return set[num] = find(set[num]); // 만약에 다르면 내가 찾는 수의 인덱스의 대표값이 뭔지 찾기
        }
    }
}