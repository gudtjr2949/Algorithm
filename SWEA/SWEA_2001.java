package coding_test.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 2001 : 파리 퇴치
public class SWEA_2001 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            String nm = bf.readLine();
            String[] nm_arr = nm.split(" ");

            int N = Integer.parseInt(nm_arr[0]);
            int M = Integer.parseInt(nm_arr[1]);

            int[][] board = new int[N][N];

            for (int j = 0 ; j < N ; j++){
                String s = bf.readLine();
                String[] s_arr = s.split(" ");
                for (int q = 0 ; q < N ; q++){
                    board[j][q] = Integer.parseInt(s_arr[q]);
                }
            }

            int start_x = 0;
            int start_y = 0;

            int answer = 0;

            while (start_y + M <= N){
                int tmp = 0;

                for (int j = start_y ; j < start_y+M ; j++){
                    for (int q = start_x ; q < start_x+M ; q++){
                        tmp += board[j][q];
                    }
                }

                answer = Math.max(answer , tmp);

                start_x++;

                if (start_x + M > N){
                    start_x = 0;
                    start_y++;
                }
            }

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
