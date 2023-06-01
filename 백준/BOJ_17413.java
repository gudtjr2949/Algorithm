package coding_test.백준;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

// 백준 17413 : 단어 뒤집기 2
public class BOJ_17413 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        ArrayList<Integer> open = new ArrayList<>();
        ArrayList<Integer> close = new ArrayList<>();
        ArrayList<Integer> space = new ArrayList<>();

        // 뒤집으면 안되는 구간
        for (int i = 0 ; i < s.length() ; i++) {
            if (s.charAt(i) == '<') {
                open.add(i);
            }
            else if (s.charAt(i) == '>'){
                close.add(i);
            }
            else if (s.charAt(i) == ' '){
                space.add(i);
            }
        }

        char[] arr = new char[s.length()];

        for (int i = 0 ; i < open.size() ; i++) {
            for (int j = open.get(i) ; j <= close.get(i) ; j++) {
                arr[j] = s.charAt(j);
            }
        }

         // 공백
        for (int i = 0 ; i < space.size() ; i++) {
            arr[space.get(i)] = ' ';
        }

        ArrayList<Integer> null_open = new ArrayList<>();
        ArrayList<Integer> null_close = new ArrayList<>();

        boolean sign = true; // true면 open, false면 close

        for (int i = 0 ; i < s.length() ; i++) {
            if (arr[i] == 0 && sign){
                null_open.add(i);
                sign = false;
            }
            else if (arr[i] != 0 && !sign) {
                null_close.add(i);
                sign = true;
            }
        }

        if (null_open.size() != null_close.size())
            null_close.add(s.length());

        for (int i = 0 ; i < null_open.size() ; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = null_open.get(i) ; j < null_close.get(i) ; j++) {
                sb.append(s.charAt(j));
            }

            int idx = 0;

            for (int j = null_close.get(i) - 1 ; j >= null_open.get(i) ; j--) {
                arr[j] = sb.charAt(idx);
                idx++;
            }
        }

        String answer = "";

        for (int i = 0 ; i < arr.length ; i++)
            answer += arr[i];

        System.out.println(answer);
    }
}
