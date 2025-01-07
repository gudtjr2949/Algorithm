import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static Node[] arr;
    static class Node {
        int first, second;
        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(bf.readLine());
            answer = 1;
            arr = new Node[N];

            for (int i = 0 ; i < N ; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                arr[i] = new Node(first, second);
            }

            solve();

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Arrays.sort(arr, (o1, o2) -> o1.first - o2.first);

        int max = arr[0].second;

        for (int i = 1 ; i < N ; i++) {
            if (arr[i].second < max) {
                answer++;
                max = arr[i].second;
            }
        }
    }
}