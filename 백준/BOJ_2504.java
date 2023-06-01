package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

// 백준 2504 : 괄호의 값
public class BOJ_2504 {

    static char[] arr;
    static ArrayList<Bracket> list = new ArrayList<>();
    static ArrayList<Bracket[]> separate_list = new ArrayList<>(); // 깊이 별로 나누기
    static int max_depth = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        arr = new char[s.length()];

        for (int i = 0 ; i < s.length() ; i++)
            arr[i] = s.charAt(i);

        check();
        separate();

//        for (int i = 0 ; i < separate_list.size() ; i++) {
            System.out.println(separate_list.get(1)[1].open_idx + " " + separate_list.get(1)[1].close_idx);
//        }
        print();
    }

    static class Bracket {

        int open_idx;
        int close_idx;
        String shape;
        int depth;

        public Bracket(int open_idx, String shape) {
            super();
            this.open_idx = open_idx;
            this.shape = shape;
        }

        public void SetClose(int close_idx) {
            this.close_idx = close_idx;
        }

        public void SetDepth(int depth) {
            this.depth = depth;
        }
    }

    private static void check() {
        Stack<Bracket> open = new Stack<>();

        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == '(') {
                open.add(new Bracket(i, "small"));
            }
            else if (arr[i] == '[') {
                open.add(new Bracket(i, "big"));
            }
            else {
                if (arr[i] == ')' || arr[i] == ']') {
                    Bracket b = open.pop();
                    b.SetClose(i);
                    b.SetDepth(find_depth(b));
                    list.add(b);
                }
            }
        }
    }

    // 깊이로 우선순위 확인..?
    private static int find_depth(Bracket b) {
        int right_depth = 0;

        // 오른쪽으로 닫는게 몇 개 있는지 확인
        for (int i = b.close_idx+1 ; i < arr.length ; i++) {
            if (arr[i] == ')' || arr[i] == ']') {
                right_depth++;
            }
            else {
                break;
            }
        }

        int left_depth = 0;

        // 왼쪽으로 여는게 몇 개 있는지 확인
        for (int i = b.open_idx-1 ; i >= 0 ; i--) {
            if (arr[i] == '(' || arr[i] == '[') {
                left_depth++;
            }
            else {
                break;
            }
        }

        max_depth = Math.max(max_depth, Math.max(right_depth, left_depth));

        return Math.max(right_depth, left_depth);
    }

    private static void separate() {
        // 기준 높이
        int cur_d = max_depth;

        int idx = 0;

        while (cur_d >= 0) {
            for (int i = idx; i < list.size(); i++) {
                if (list.get(i).depth == cur_d) {
                    Bracket[] b = new Bracket[i+1 - idx];

                    for (int j = idx ; j <= i ; j++) {
                        b[j] = list.get(j);
                    }

                    idx = i;
                    separate_list.add(b);

                    break;
                }
            }

            cur_d--;
        }
    }

    private static void print() {
        for (int i = 0 ; i < list.size() ; i++) {
            System.out.println(list.get(i).open_idx + ", " + list.get(i).close_idx + " / 모양 " + list.get(i).shape + " / 깊이 " + list.get(i).depth);
        }
    }
}