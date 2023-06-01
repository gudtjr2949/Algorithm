package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 백준 2493 : 탑
public class BOJ_2493 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        String s = bf.readLine();
        String[] s_arr = s.split(" ");

        int[] arr = new int[N];
        int[] answer = new int[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(s_arr[i]);
        }

        Stack<Tower> stack = new Stack<>();

        stack.push(new Tower(1, arr[0]));

        for (int i = 1 ; i < N ; i++) {
            if (stack.peek().height > arr[i]) { // 왼쪽에 더 큰 탑 찾았으면
                // 넣기
                answer[i] = stack.peek().idx;
            }
            else { // 바로 옆에 없으면 계속 탐색
                // 더 큰거 찾기
                while (!stack.isEmpty()) {
                    if (stack.peek().height > arr[i]) {
                        answer[i] = stack.peek().idx;

                        break;
                    }

                    stack.pop();
                }
            }
            stack.push(new Tower(i+1, arr[i]));
        }

        print(answer);

    }

    private static void print(int[] answer) {
        for (int i = 0 ; i < answer.length ; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}

class Tower {
    int idx;
    int height;

    public Tower(int idx, int height) {
        // TODO Auto-generated constructor stub
        this.idx = idx;
        this.height = height;
    }
}
