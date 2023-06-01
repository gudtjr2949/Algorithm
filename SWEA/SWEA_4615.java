import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 4615 : 재미있는 오셀로 게임
public class SWEA_4615 {
    static int[] ny = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] nx = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0 ; i < Test ; i++){
            String s = bf.readLine();
            String[] s_arr = s.split(" ");

            int N = Integer.parseInt(s_arr[0]);
            int M = Integer.parseInt(s_arr[1]);

            int[] b_x = new int[M];
            int[] b_y = new int[M];

            int[] w_x = new int[M];
            int[] w_y = new int[M];

            char[][] board = new char[N][N];

            int[] term = new int[M];

            if (N == 4) {
                board[1][1] = 'W';
                board[2][1] = 'B';
                board[2][2] = 'W';
                board[1][2] = 'B';
            }
            else if (N == 6) {
                board[2][2] = 'W';
                board[3][2] = 'B';
                board[3][3] = 'W';
                board[2][3] = 'B';
            }

            else if (N == 8){
                board[3][3] = 'W';
                board[4][3] = 'B';
                board[4][4] = 'W';
                board[3][4] = 'B';
            }

            int b_idx = 0;
            int w_idx = 0;

            for (int j = 0 ; j < M ; j++){
                String v = bf.readLine();
                String[] v_arr = v.split(" ");

                if (Integer.parseInt(v_arr[2]) == 1){
                    b_x[b_idx] = Integer.parseInt(v_arr[0]) - 1;
                    b_y[b_idx] = Integer.parseInt(v_arr[1]) - 1;
                    b_idx++;
                }
                else {
                    w_x[w_idx] = Integer.parseInt(v_arr[0]) - 1;
                    w_y[w_idx] = Integer.parseInt(v_arr[1]) - 1;
                    w_idx++;
                }

                term[j] = Integer.parseInt(v_arr[2]);
            }

            b_idx = 0;
            w_idx = 0;

            // 입력 끝

            // 이제 보드에 입력하기

            Loop:
            for (int j = 0 ; j < M ; j++){
                if (term[j] == 1) { // 흑돌
                    board[b_y[b_idx]][b_x[b_idx]] = 'B';
                    search(board, b_x[b_idx], b_y[b_idx], 1, N);
                    b_idx++;
                }
                else { // 백돌
                    board[w_y[w_idx]][w_x[w_idx]] = 'W';
                    search(board, w_x[w_idx], w_y[w_idx], 2, N);
                    w_idx++;
                }

                // 보드에 빈 칸이 없으면?
                for (int q = 0 ; q < N ; q++){
                    for (int k = 0 ; k < N ; k++){
                        if (board[q][k] == ' '){
                            break Loop;
                        }
                    }
                }
            }

            int b_cnt = 0;
            int w_cnt = 0;

            for (int j = 0 ; j < N ; j++){
                for (int q = 0 ; q < N ; q++){
                    if (board[j][q] == 'W')
                        w_cnt++;
                    else if (board[j][q] == 'B')
                        b_cnt++;
                }
            }


            sb.append("#").append(i+1).append(" ").append(b_cnt).append(" ").append(w_cnt).append("\n");
        }
        System.out.println(sb);
    }

    static void search(char[][] board, int x, int y, int tmp, int N) {
        for (int i = 0 ; i < 8 ; i++){

            int dx = x + nx[i];
            int dy = y + ny[i];

            if (tmp == 1){ // 흑돌
                int cnt = 0;
                boolean sign = false;

                for (int j = 0 ; j < N ; j++){
                    int tmp_x = dx + (nx[i] * j);
                    int tmp_y = dy + (ny[i] * j);

                    if (tmp_x >= 0 && tmp_x < N && tmp_y >= 0 && tmp_y < N) {
                        if (board[tmp_y][tmp_x] == 'W') {
                            cnt++;
                        } else if (board[tmp_y][tmp_x] == 'B') {
                            sign = true;
                            break;
                        } else { // 비어있을 때
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }

                if (sign) {
                    for (int j = 0; j < cnt; j++) {
                        int cnt_x = dx + (nx[i] * j);
                        int cnt_y = dy + (ny[i] * j);

                        board[cnt_y][cnt_x] = 'B';
                    }
                }
            }

            else { // 백돌
                int cnt = 0;
                boolean sign = false;

                for (int j = 0 ; j < N ; j++){
                    int tmp_x = dx + (nx[i] * j);
                    int tmp_y = dy + (ny[i] * j);

                    if (tmp_x >= 0 && tmp_x < N && tmp_y >= 0 && tmp_y < N) {
                        if (board[tmp_y][tmp_x] == 'B') {
                            cnt++;
                        } else if (board[tmp_y][tmp_x] == 'W') {
                            sign = true;
                            break;
                        } else { // 비어있을 때
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }

                if (sign) {
                    for (int j = 0; j < cnt; j++) {
                        int cnt_x = dx + (nx[i] * j);
                        int cnt_y = dy + (ny[i] * j);

                        board[cnt_y][cnt_x] = 'W';
                    }
                }
            }
        }
    }
}
