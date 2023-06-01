import java.io.*;

public class SWEA_2805 {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++){
            int N = Integer.parseInt(bf.readLine());

            int tmp = N/2;

            int[][] board = new int[N][N];

            int sum = 0;

            for (int j = 0 ; j < N ; j++){
                String s = bf.readLine();

                for (int q = 0 ; q < N ; q++){
                    board[j][q] = s.charAt(q) - 48;
                    sum += board[j][q];
                }
            }

            int tmp_sum = 0;

            int tmp_2 = tmp;

            // 1번
            for (int j = 0 ; j < tmp ; j++){ // 갯수
                for (int q = 0 ; q < tmp_2 ; q++){
                    tmp_sum += board[q][j];
                }
                tmp_2--;
            }

            tmp_2 = tmp;

            // 2번
            for (int j = 0 ; j < tmp ; j++){ // 갯수
                for (int q = 0 ; q < tmp_2 ; q++){
                    tmp_sum += board[q][(N-1) - j];
                }
                tmp_2--;
            }

            tmp_2 = tmp;

            // 3번
            for (int j = 0 ; j < tmp ; j++){ // 갯수
                for (int q = 0 ; q < tmp_2 ; q++){
                    tmp_sum += board[(N - 1) - q][j];
                }
                tmp_2--;
            }

            tmp_2 = tmp;

            // 4번
            for (int j = 0 ; j < tmp ; j++){ // 갯수
                for (int q = 0 ; q < tmp_2 ; q++){
                    tmp_sum += board[(N - 1) - q][(N - 1) - j];
                }
                tmp_2--;
            }

            sum -= tmp_sum;

            sb.append("#").append(i+1).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);
    }
}
