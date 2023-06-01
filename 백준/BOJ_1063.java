package coding_test.백준;

import java.io.*;

// 백준 1063 : 킹
public class BOJ_1063 {

    static String[] move = {"LT", "T", "RT", "R", "RB", "B", "LB", "L"};
    static int[] nx = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] ny = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] idx = {7, 6, 5, 4, 3, 2, 1, 0};
    static char[] x = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    static int[] y = {8, 7, 6, 5, 4, 3, 2, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        char[][] board = new char[8][8];

        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        String king = s_arr[0];
        int king_x = king.charAt(0) - 'A';
        int king_y = idx[king.charAt(1) - 49];

        String stone = s_arr[1];
        int stone_x = stone.charAt(0) - 'A';
        int stone_y = idx[stone.charAt(1) - 49];

        int N = Integer.parseInt(s_arr[2]);

        board[king_y][king_x] = 'K';
        board[stone_y][stone_x] = 'S';

        String[] direction = new String[N];

        for (int i = 0 ; i < N ; i++) {
            direction[i] =  bf.readLine();
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < 8 ; j++) {
                int dy = king_y + ny[j]; // 다음 킹의 y좌표
                int dx = king_x + nx[j]; // 다음 킹의 x좌표

                int stone_dy = stone_y + ny[j]; // 다음 돌의 y좌표
                int stone_dx = stone_x + nx[j]; // 다음 돌의 x좌표

                if (direction[i].equals(move[j])){
                    if (dy >= 0 && dy < 8 && dx >= 0 && dx < 8){ // 킹의 다음 이동위치가 이동 가능한 위치인지
                        if (board[dy][dx] == 'S') { // 그 위치에 돌이 있는지
                            if (stone_dy >= 0 && stone_dy < 8 && stone_dx >= 0 && stone_dx < 8) { // 그 돌의 다음 이동 위치가 가능한 위치에 있는지
                                board[king_y][king_x] = ' '; // 본인이 있는 위치 초기화
                                board[stone_dy][stone_dx] = ' ';

                                board[dy][dx] = 'K';
                                board[stone_dy][stone_dx] = 'S';

                                // x,y 깂 현재 위치로 변경
                                king_y = dy;
                                king_x = dx;

                                stone_y = stone_dy;
                                stone_x = stone_dx;
                            }
                        }
                        else { // 킹이 이동하려는 위치에 돌은 없지만, 킹이 이동하려는 위치가 이동 가능한 범위 안에 있는 경우
                            board[king_y][king_x] = ' ';

                            board[dy][dx] = 'K';

                            king_y = dy;
                            king_x = dx;
                        }
                    }

                }

            }
        }

        int answer_king_x = 0;
        int answer_king_y = 0;

        int answer_stone_x = 0;
        int answer_stone_y = 0;

        for (int i = 0 ; i < 8 ; i++) {
            for (int j = 0 ; j < 8 ; j++) {
                if (board[i][j] == 'K'){
                    answer_king_x = j;
                    answer_king_y = i;
                }
                else if (board[i][j] == 'S') {
                    answer_stone_x = j;
                    answer_stone_y = i;
                }
            }
        }

        System.out.println(x[answer_king_x] + "" + y[answer_king_y]);
        System.out.println(x[answer_stone_x] + "" + y[answer_stone_y]);
    }
}
