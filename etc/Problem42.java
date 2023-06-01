import java.util.*;
import java.io.*;

public class Problem42 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            String s1 = bf.readLine();
            String[] s1_arr = s1.split(" ");

            int N = Integer.parseInt(s1_arr[0]);
            int now_y = Integer.parseInt(s1_arr[1]) - 1;
            int now_x = Integer.parseInt(s1_arr[2]) - 1;
            int jumper_N = Integer.parseInt(s1_arr[3]);

            int[] jumper_y = new int[jumper_N];
            int[] jumper_x = new int[jumper_N];

            String s2 = bf.readLine();
            String[] s2_arr = s2.split(" ");

            int idx = 0;

            for (int j = 0; j < jumper_N * 2; j += 2) {
                jumper_y[idx] = Integer.parseInt(s2_arr[j]) - 1;
                jumper_x[idx] = Integer.parseInt(s2_arr[j + 1]) - 1;

                idx++;
            }

            int move_N = Integer.parseInt(bf.readLine());

            int[] move_dir = new int[move_N];
            int[] move_num = new int[move_N];

            String s3 = bf.readLine();
            String[] s3_arr = s3.split(" ");

            idx = 0;
            for (int j = 0; j < move_N * 2; j += 2) {
                move_dir[idx] = Integer.parseInt(s3_arr[j]);
                move_num[idx] = Integer.parseInt(s3_arr[j + 1]);

                idx++;
            }


            boolean[][] board = new boolean[N][N];

            for (int j = 0; j < jumper_N; j++) {
                board[jumper_y[j]][jumper_x[j]] = true;
            }

            boolean sign = false; // 범위 밖으로 나갔거나, 점퍼를 만났는지 확인하는 변수
            Loop:
            for (int j = 0; j < move_N; j++) {
                if (move_dir[j] == 1 && now_y - move_num[j] >= 0) {
                    for (int q = now_y - 1 ; q > now_y - move_num[j] ; q--){
                        if (board[q][now_x]){
                            sign = true;
                            break Loop;
                        }
                    }

                    now_y -= move_num[j];
                }

                else if (move_dir[j] == 2 && now_x + move_num[j] < N) {
                    for (int q = now_x + 1 ; q < now_x + move_num[j] ; q++){
                        if (board[now_y][q]){
                            sign = true;
                            break Loop;
                        }
                    }

                    now_x += move_num[j];
                }

                else if (move_dir[j] == 3 && now_y + move_num[j] < N) {
                    for (int q = now_y + 1 ; q < now_y + move_num[j] ; q++){
                        if (board[q][now_x]){
                            sign = true;
                            break Loop;
                        }
                    }

                    now_y += move_num[j];
                }

                else if (move_dir[j] == 4 && now_x - move_num[j] >= 0){
                    for (int q = now_x - 1 ; q > now_x + move_num[j] ; q--){
                        if (board[now_y][q]){
                            sign = true;
                            break Loop;
                        }
                    }

                    now_x -= move_num[j];
                }

                else { // 범위 밖으로 나온 경우
                    sign = true;
                    break;
                }
            }

            int answer_x = 0;
            int answer_y = 0;

            if (!sign) {
                answer_x = now_x + 1;
                answer_y = now_y + 1;
            }

            sb.append("#").append(i + 1).append(" ").append(answer_y).append(" ").append(answer_x).append("\n");
        }
        System.out.println(sb);
    }
}
