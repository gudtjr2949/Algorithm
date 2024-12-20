import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static Node[] arr;
    static class Node {
        int t, s;
        public Node(int t, int s) {
            this.t = t;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new Node[N];

        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            arr[i] = new Node(t, s);
        }

        solve();

        System.out.println(answer);
    }

    static void solve() {
        Arrays.sort(arr, (o1, o2) -> {
            if (o1.s == o2.s) return o1.t - o2.t;
            return o2.s - o1.s;
        });

        answer = arr[0].s - arr[0].t; // answer는 i번째 일을 가능한 늦게 시작하는 시간

        for (int i = 1 ; i < N ; i++) {
            if (arr[i].s < answer) { // 이전 일과 i번째 일이 무관한 경우, answer를 i번째 일을 기준으로 변경
                answer = arr[i].s - arr[i].t;
            } else {
                answer -= arr[i].t;
            }
        }

        if (answer < 0) answer = -1;
    }
}