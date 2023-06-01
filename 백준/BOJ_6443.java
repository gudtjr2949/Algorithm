package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// 백준 6443 : 애너그램
public class BOJ_6443 {

    static ArrayList<String> list;
    static StringBuilder sb = new StringBuilder();
    static char[] arr;
    static char[] origin; // 중복 방지용

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        for (int i = 0; i < N; i++) {
            arr = bf.readLine().toCharArray();
            Arrays.sort(arr);
            list = new ArrayList<>();
            origin = new char[arr.length];
            solve(0, new char[arr.length], arr, new boolean[arr.length]);

            print();
        }

        System.out.println(sb);

    }

    private static void print() {
        for (int i = 0 ; i < list.size() ; i++) {
            sb.append(list.get(i)).append("\n");
        }
    }

    private static void solve(int idx, char[] input, char[] s, boolean[] visited) {
        if (idx == input.length) {
            list.add(array2Str(input));
            return;
        }

        origin[idx] = ' '; // 이 전의 경우에 idx에 원래 오던 알파벳

        for (int i = 0 ; i < s.length ; i++) {
            if (!visited[i] && origin[idx] != s[i]) {
                origin[idx] = s[i];
                visited[i] = true;
                input[idx] = s[i];
                solve(idx+1, input, s, visited);
                visited[i] = false;
            }
        }
    }

    private static String array2Str(char[] input) {
        String s = "";

        for (int i = 0 ; i < input.length ; i++)
            s += input[i];

        return s;
    }
}
