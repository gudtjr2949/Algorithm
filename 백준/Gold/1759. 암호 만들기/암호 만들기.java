import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int L, C;
    static char[] arr, input, alpha = {'a', 'e', 'i', 'o', 'u'};
    static boolean[] visited;
    static List<String> answer;
    static Set<String> set;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        input = new char[L];
        set = new HashSet<>();
        answer = new ArrayList<>();
        visited = new boolean[C];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < C ; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0);

        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        for (String s : answer) {
            sb.append(s).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int idx, int cur) {
        if (idx == L) {
            makeString();
            return;
        }

        for (int i = cur ; i < C ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = arr[i];
                dfs(idx+1, i+1);
                visited[i] = false;
            }
        }
    }

    static void makeString() {
        int monCnt = 0;
        int sonCnt = 0;

        for (int i = 0 ; i < L ; i++) {
            for (int j = 0 ; j < 5 ; j++) {
                if (input[i] == alpha[j]) {
                    monCnt++;
                    break;
                }
            }
        }

        sonCnt = L - monCnt;

        if (monCnt >= 1 && sonCnt >= 2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0 ; i < L ; i++) {
                sb.append(input[i]);
            }

            if (!set.contains(sb.toString())) {
                set.add(sb.toString());
                answer.add(sb.toString());
            }
        }
    }
}