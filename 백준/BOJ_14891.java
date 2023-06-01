package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 백준 14891 : 톱니바퀴
public class BOJ_14891 {

    static Deque<Character> down[];
    static Deque<Character> up[];
    static boolean[] sign;
    static boolean[] dir_arr;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        down = new Deque[5];
        up = new Deque[5];
        sign = new boolean[4];
        dir_arr = new boolean[5];
        answer = 0;

        for (int i = 0; i < 5; i++) {
            down[i] = new ArrayDeque<>();
            up[i] = new ArrayDeque<>();
        }

        String a = bf.readLine();
        String b = bf.readLine();
        String c = bf.readLine();
        String d = bf.readLine();
        setDeque(a, b, c, d);

        int K = Integer.parseInt(bf.readLine());

        for (int i = 0; i < K; i++) {
            String s = bf.readLine();
            String[] s_arr = s.split(" ");
            check();
            setDir(Integer.parseInt(s_arr[0]), Integer.parseInt(s_arr[1]));
            spin(Integer.parseInt(s_arr[0]));
        }

        cnt();

        System.out.println(answer);
    }

    private static void cnt() {

        for (int i = 1 ; i <= 4 ; i++) {
            up[i].pollLast();
        }

        for (int i = 1 ; i <= 4 ; i++) {
            if (up[i].peekLast() == '1') {
                answer += Math.pow(2, i-1);
            }
        }
    }

    private static void spin(int idx) {

        // 시계
        if (dir_arr[idx]){
            char tmp = up[idx].pollLast();
            down[idx].addLast(tmp);

            tmp = down[idx].pollFirst();
            up[idx].addFirst(tmp);
        }
        else {
            char tmp = up[idx].pollFirst();
            down[idx].addFirst(tmp);

            tmp = down[idx].pollLast();
            up[idx].addLast(tmp);
        }

        // 움직인 바퀴의 오른쪽
        for (int i = idx + 1 ; i <= 4 ; i++) {
            if (!sign[i-1]) {
                if (dir_arr[i]){
                    char tmp = up[i].pollLast();
                    down[i].addLast(tmp);

                    tmp = down[i].pollFirst();
                    up[i].addFirst(tmp);
                }
                else {
                    char tmp = up[i].pollFirst();
                    down[i].addFirst(tmp);

                    tmp = down[i].pollLast();
                    up[i].addLast(tmp);
                }
            }
            else {
                break;
            }
        }

        // 움직인 바퀴의 왼쪽
        for (int i = idx - 1 ; i >= 1 ; i--) {
            if (!sign[i]) {
                if (dir_arr[i]){
                    char tmp = up[i].pollLast();
                    down[i].addLast(tmp);

                    tmp = down[i].pollFirst();
                    up[i].addFirst(tmp);
                }
                else {
                    char tmp = up[i].pollFirst();
                    down[i].addFirst(tmp);

                    tmp = down[i].pollLast();
                    up[i].addLast(tmp);
                }
            }
            else {
                break;
            }
        }
    }

    private static void setDir(int idx, int dir) {
        if (dir == -1) {
            dir_arr[idx] = false;
        } else {
            dir_arr[idx] = true;
        }

        for (int i = idx + 1; i < 5; i++) {
            if (!dir_arr[i - 1]) {
                dir_arr[i] = true;
            } else {
                dir_arr[i] = false;
            }
        }
        for (int i = idx - 1; i >= 1; i--) {
            if (!dir_arr[i + 1]) {
                dir_arr[i] = true;
            } else {
                dir_arr[i] = false;
            }
        }
    }

    private static void check() {
        for (int i = 1; i <= 3; i++) {
            if (down[i].peekLast() == up[i + 1].peekFirst()) {
                sign[i] = true;
            } else {
                sign[i] = false;
            }
        }
    }

    private static void setDeque(String a, String b, String c, String d) {
        String[] s_arr = {a, b, c, d};

        for (int i = 1; i <= 4; i++) {
            down[i].addFirst(s_arr[i - 1].charAt(2));
            down[i].addFirst(s_arr[i - 1].charAt(3));
            down[i].addFirst(s_arr[i - 1].charAt(4));
            down[i].addFirst(s_arr[i - 1].charAt(5));
            up[i].addLast(s_arr[i - 1].charAt(6));
            up[i].addLast(s_arr[i - 1].charAt(7));
            up[i].addLast(s_arr[i - 1].charAt(0));
            up[i].addLast(s_arr[i - 1].charAt(1));
        }
    }
}
