import java.io.*;
import java.util.ArrayList;

// SWEA 1234 : 비밀번호
public class SWEA_1234 {

    /*
    * 조건 1 : 마주하고 있는 수가 같으면 remove
    * 조건 2 : N의 범위도 작고 제한시간도 넉넉하니까 조건 1 발생한 순간 바로 처음부터 다시
    * 조건 3 : 만약에 마주하는 수가 다르면 다음 인덱스 검사
    * 조건 4 : list의 크기를 벗어나면 인덱스 초기화
    * 조건 5 : 마주하는 수가 다른 횟수가 (리스트의 크기-1) 와 같으면 중복된 수 모두 제거한 상황 -> 반복문 탈출 */

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < 10 ; i++){
            String s = bf.readLine();
            String[] s_arr = s.split(" ");
            int N = Integer.parseInt(s_arr[0]);

            ArrayList<Integer> list = new ArrayList<>();

            for (int j = 0 ; j < N ; j++){
                list.add(s_arr[1].charAt(j) - 48);
            }

            int idx = 0;

            while (true) {
                if (list.get(idx) == list.get(idx+1)){ // 조건 1
                    list.remove(idx);
                    list.remove(idx);
                    idx = 0; // 조건 2
                }
                else { // 조건 3
                    idx++;
                }

                if (idx == list.size()-1) // 조건 4
                    idx = 0;

                int cnt = 0;

                for (int j = 0 ; j < list.size()-1 ; j++){ // 조건 5
                    if (list.get(j) != list.get(j+1)){
                        cnt++;
                    }
                }

                if (cnt == list.size()-1){ // 조건 5
                    break;
                }
            }

            String answer = "";

            for (int j = 0 ; j < list.size() ; j++){
                answer += list.get(j);
            }

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
