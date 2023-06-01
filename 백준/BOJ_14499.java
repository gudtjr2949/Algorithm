package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 14499 : 주사위 굴리기
public class BOJ_14499 {

    static int[][] dice;
    static int[][] map;
    static int order;
    static int dice_x, dice_y;
    static int[] nx = {0, 1, -1, 0, 0};
    static int[] ny = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s1 = bf.readLine();
        String[] s1_arr = s1.split(" ");

        // 초기 주사위
        dice = new int[4][3];

        map = new int[Integer.parseInt(s1_arr[0])][Integer.parseInt(s1_arr[1])];
        dice_y = Integer.parseInt(s1_arr[2]);
        dice_x = Integer.parseInt(s1_arr[3]);
        order = Integer.parseInt(s1_arr[4]);

        for (int i = 0 ; i < map.length ; i++) {
            String s2 = bf.readLine();
            String[] s2_arr = s2.split(" ");
            for (int j = 0 ; j < map[0].length ; j++) {
                map[i][j] = Integer.parseInt(s2_arr[j]);
            }
        }

        String s3 = bf.readLine();
        String[] s3_arr = s3.split(" ");
        for (int i = 0 ; i < order ; i++) {
            int dir = Integer.parseInt(s3_arr[i]);
            if (!(dice_x + nx[dir] >= map[0].length || dice_x + nx[dir] < 0 || dice_y + ny[dir] >= map.length || dice_y + ny[dir] < 0)) {
                move(Integer.parseInt(s3_arr[i]));
                check();
                sb.append(dice[1][1]).append("\n");
            }
        }

        System.out.println(sb);
    }
    private static void check() {
        // 주사위 굴렸을 때 이동한 칸의 숫자가 0인 경우
        if (map[dice_y][dice_x] == 0) {
            map[dice_y][dice_x] = dice[3][1];
        }
        else {
            dice[3][1] = map[dice_y][dice_x];
            map[dice_y][dice_x] = 0;
        }
    }

    private static void move(int dir) {
        // 동쪽
        if (dir == 1) {
            int tmp_1 = dice[3][1];
            int tmp_2 = dice[1][2];

            dice[1][2] = dice[1][1];
            dice[1][1] = dice[1][0];
            dice[1][0] = tmp_1;
            dice[3][1] = tmp_2;

            dice_x += nx[dir];
            dice_y += ny[dir];
        }
        // 서쪽
        else if (dir == 2) {
            int tmp_1 = dice[1][0];
            int tmp_2 = dice[3][1];

            dice[1][0] = dice[1][1];
            dice[1][1] = dice[1][2];
            dice[1][2] = tmp_2;
            dice[3][1] = tmp_1;
            dice_x += nx[dir];
            dice_y += ny[dir];
        }
        // 북쪽
        else if (dir == 3) {
            int tmp = dice[0][1];

            dice[0][1] = dice[1][1];
            dice[1][1] = dice[2][1];
            dice[2][1] = dice[3][1];
            dice[3][1] = tmp;

            dice_x += nx[dir];
            dice_y += ny[dir];
        }
        // 남쪽
        else if (dir == 4){
            int tmp = dice[3][1];

            dice[3][1] = dice[2][1];
            dice[2][1] = dice[1][1];
            dice[1][1] = dice[0][1];
            dice[0][1] = tmp;

            dice_x += nx[dir];
            dice_y += ny[dir];
        }
    }
}
