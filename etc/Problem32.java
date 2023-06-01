import java.io.*;

public class Problem32 {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int i = 0; i < Test; i++) {
            String xyn = bf.readLine();
            String[] xyn_arr = xyn.split(" ");

            int Y = Integer.parseInt(xyn_arr[0]);
            int X = Integer.parseInt(xyn_arr[1]);
            int N = Integer.parseInt(xyn_arr[2]);

            int[][] board = new int[Y][X];

            int[] attend_x = new int[N];
            int[] attend_y = new int[N];
            int[] attend_N = new int[N];

            int[] attender_money = new int[N];

            for (int j = 0; j < Y; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");
                for (int q = 0; q < X; q++) {
                    board[j][q] = Integer.parseInt(s_arr[q]);
                }
            }


            for (int j = 0; j < N; j++) {
                String s = bf.readLine();
                String[] s_arr = s.split(" ");
                attend_y[j] = Integer.parseInt(s_arr[0]) - 1;
                attend_x[j] = Integer.parseInt(s_arr[1]) - 1;
                attend_N[j] = Integer.parseInt(s_arr[2]);
            }

            String tmp = bf.readLine();
            String[] tmp_arr = tmp.split(" ");

            int trap_N = Integer.parseInt(tmp_arr[0]);

            int[] trap_x = new int[trap_N];
            int[] trap_y = new int[trap_N];

            int idx = 0;

            for (int j = 1; j < tmp_arr.length; j+=2) {
                trap_y[idx] = Integer.parseInt(tmp_arr[j]) - 1;
                trap_x[idx] = Integer.parseInt(tmp_arr[j + 1]) - 1;
            }

            // 함정 설정
            for (int j = 0 ; j < trap_N ; j++){
                board[trap_y[j]][trap_x[j]] = 0;
            }

            // 참가자 별 이동 시작
            for (int j = 0 ; j < N ; j++) { // 참가자 정하고
                boolean sign = true;

                for (int q = 0 ; q < attend_N[j] ; q++) { // 해당 참가자 이동 횟수만큼 반복문 실행

                    int now = board[attend_y[j]][attend_x[j]] / 10; // 이동 방향
                    int move_time = board[attend_y[j]][attend_x[j]] - (now * 10); // 점프 칸 수

                    if (now == 1 && attend_x[j] + move_time < X){ // 오른쪽 이동
                        attend_x[j] += move_time;
                    }

                    else if (now == 2 && attend_y[j] + move_time < Y){ // 아래로 이동
                        attend_y[j] += move_time;
                    }

                    else if (now == 3 && attend_x[j] - move_time >= 0){ // 왼쪽으로 이동
                        attend_x[j] -= move_time;
                    }

                    else if (now == 4 && attend_y[j] - move_time >= 0){ // 위로 이동
                        attend_y[j] -= move_time;
                    }

                    else { // 함정을 만났거나, 범위에서 벗어난 경우
                        sign = false;
                        break;
                    }

                    if (board[attend_y[j]][attend_x[j]] == 0){ // 점프했지만 도착 지점이 함정인 경우
                        sign = false;
                        break;
                    }

                }

                if (sign) { // 상금 획득
                    attender_money[j] += (board[attend_y[j]][attend_x[j]] * 100) - 1000;
                }

                else {
                    attender_money[j] -= 1000;
                }
            }

            int answer = 0;

            for (int j = 0 ; j < N ; j++){
                answer += attender_money[j];
            }

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
