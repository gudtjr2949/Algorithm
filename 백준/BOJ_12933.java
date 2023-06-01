package coding_test.백준;

import java.io.*;
import java.util.*;

// 백준 12933 : 오리
public class BOJ_12933 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        char[] arr = new char[s.length()];

        char[] alpha = {'q', 'u', 'a', 'c', 'k'};

        int cnt = 0;

        ArrayList<QK> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);

            if (arr[i] == 'q') {
                cnt++;
            }
        }

        ArrayList<Integer> q = new ArrayList<>();
        ArrayList<Integer> k = new ArrayList<>();

        // 'q' 가 등장한 인덱스와 'k'가 등장한 인덱스 저장
        for (int i = 0 ; i < s.length() ; i++) {
            if (arr[i] == 'q'){
                q.add(i);
            }
            else if (arr[i] == 'k') {
                k.add(i);
            }
        }

        boolean sign = true;

        int max = 0;

        if (q.size() != k.size()) { // q와 k의 갯수가 다르다? 애초에 답이 아닌 경우
            sign = false;
        }

        else {
            for (int i = 0 ; i < cnt ; i++) { // 각각 순서대로 q와 k의 인덱스를 리스트에 저장. ex) (0, 10), (2, 12), (4, 15) ...
                list.add(new QK(q.get(i), k.get(i)));
            }

            int[] bark_cnt = new int[s.length()];

            // 그 범위만큼 cnt
            for (int i = 0 ; i < list.size() ; i++){
                for (int j = list.get(i).q_idx ; j < list.get(i).k_idx ; j++){
                    bark_cnt[j]++;
                }
            }

            for (int i = 0 ; i < s.length() ; i++){
                max = Math.max(max, bark_cnt[i]);
            }

            int idx = 0;

            String tmp = "";

            // 이제 quack으로 이루어져 있는지 확인
            for (int e = 0 ; e < cnt ; e++) {
                for (int i = 0; i < 5; i++) {
                    for (int j = idx; j < s.length(); j++) {
                        if (alpha[i] == arr[j]) {
                            idx = j;
                            tmp += alpha[i];
                            arr[j] = ' '; // 찾았으면 해당 인덱스를 빈칸으로 만듬
                            break;
                        }
                    }
                }

                if (tmp.equals("")){
                    break;
                }
                else if (!tmp.equals("quack")){
                    sign = false;
                    break;
                }

                idx = 0;
                tmp = "";
            }

            // 반복문을 빠져나왔는데 빈칸이 아닌 곳이 있다? 정상적으로 끝나지 못함
            for (int i = 0 ; i < s.length() ; i++) {
                if (arr[i] != ' ') {
                    sign = false;
                    break;
                }
            }
        }

        if (sign) {
            System.out.println(max);
        }
        else {
            System.out.println(-1);
        }
    }
}

class QK {
    int q_idx;
    int k_idx;

    QK(int q_idx, int k_idx){
        this.q_idx = q_idx;
        this.k_idx = k_idx;
    }
}