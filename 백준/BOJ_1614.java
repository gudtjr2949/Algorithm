import java.io.*;
import java.util.*;

// 백준 1614 : 영식이의 손가락
public class BOJ_1614 {
    /*
    * 조건 1 : 다친 손가락 사용 횟수 최대가 10억임. 제한시간 2초이므로 반복문 사용 금지*/

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int injury_finger = Integer.parseInt(bf.readLine());
        long N = Long.parseLong(bf.readLine());

        long cnt = injury_finger;

        if (injury_finger == 1){
            cnt += 8 * N;
        }

        else if (injury_finger == 2){
            if (N % 2 != 0){
                cnt += (6 * ((N/2) + 1)) + (2 * (N/2));
            }
            else {
                cnt += (6 * (N/2)) + (2 * (N/2));
            }
        }

        else if (injury_finger == 3){
            cnt += 4 * N;
        }

        else if (injury_finger == 4){
            if (N % 2 != 0){
                cnt += (6 * (N/2)) + (2 * ((N/2)+1));
            }
            else {
                cnt += (6 * (N/2)) + (2 * (N/2));
            }
        }

        else {
            cnt += 8 * N;
        }

        System.out.println(cnt-1);
    }
}
