import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static char[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N];
        visited = new boolean[N];

        String s = bf.readLine();
        for (int i = 0 ; i < N ; i++) {
            arr[i] = s.charAt(i);
        }

        int answer = 0;

        for (int i = 0 ; i < N ; i++) {
            if (arr[i] == 'P' && findH(i)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean findH(int idx) {
        // 왼쪽부터
        for (int i = idx-K ; i < idx ; i++) {
            if (i >= 0 && !visited[i] && arr[i] == 'H') {
                visited[i] = true;
                return true;
            }
        }

        // 오른쪽
        for (int i = idx+1 ; i <= idx+K ; i++) {
            if (i < N && !visited[i] && arr[i] == 'H') {
                visited[i] = true;
                return true;
            }
        }

        return false;
    }
}