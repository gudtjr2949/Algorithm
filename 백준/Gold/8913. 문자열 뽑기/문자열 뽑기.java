import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static boolean possible;
    static boolean[] visited;
    static Set<String> set;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            String s = bf.readLine();
            visited = new boolean[s.length()];
            possible = false;
            set = new HashSet<>();
            dfs(s);
            sb.append(possible ? 1 : 0).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(String s) {
        if (possible) return;

        int cnt = 1;
        int startIdx = 0;

        for (int i = 1 ; i < s.length() ; i++) {
            if (s.charAt(i) == s.charAt(i-1)) cnt++;
            else {
                if (cnt >= 2)
                    dfs(s.substring(0, startIdx) + s.substring(startIdx + cnt, s.length()));
                startIdx = i;
                cnt = 1;
            }
        }

        if (s.length() >= 2 && cnt == s.length()) possible = true;
    }
}