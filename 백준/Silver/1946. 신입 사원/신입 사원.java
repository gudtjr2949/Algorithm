import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static Node[] arr;
    static class Node {
        int first, second, rank;

        public Node(int first, int second, int rank) {
            this.first = first;
            this.second = second;
            this.rank = rank;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            answer = 1;
            N = Integer.parseInt(bf.readLine());
            arr = new Node[N];
            for (int i = 0 ; i < N ; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
            }

            solve();

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        Arrays.sort(arr, (o1, o2) -> o1.first - o2.first);

        for (int i = 0 ; i < N ; i++)
            arr[i].rank = i;


        Arrays.sort(arr, (o1, o2) -> o1.second - o2.second);

        int mostHighRank = arr[0].rank;

        for (int i = 1 ; i < N ; i++) {
            if (arr[i].rank < mostHighRank) {
                mostHighRank = arr[i].rank;
                answer++;
            }
        }
    }
}