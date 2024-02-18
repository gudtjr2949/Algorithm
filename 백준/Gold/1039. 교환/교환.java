import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = 0;
    static class Number {
        int num, time;

        public Number(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(answer == 0 ? -1 : answer);
    }

    static void bfs() {
        Queue<Number> Q = new LinkedList<>();
        boolean[][] visited = new boolean[M+1][1000001];
        Q.add(new Number(N, 0));
        visited[0][N] = true;

        while (!Q.isEmpty()) {
            Number n = Q.poll();

            if (n.time == M) {
                answer = Math.max(answer, n.num);
                continue;
            }

            int length = Integer.toString(n.num).length();

            for (int i = 0 ; i < length-1 ; i++) {
                for (int j = i+1 ; j < length ; j++) {
                    int swap = swapNumber(n.num, i, j);

                    if (swap != -1 && !visited[n.time+1][swap]) {
                        visited[n.time+1][swap] = true;
                        Q.add(new Number(swap, n.time+1));
                    }
                }
            }
        }
    }

    static int swapNumber(int num, int i, int j) {
        char[] arr = Integer.toString(num).toCharArray();

        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

        if (i == 0 && arr[i] == '0') return -1;
        else return Integer.parseInt(String.valueOf(arr));
    }
}