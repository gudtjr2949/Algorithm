import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String s;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        while (T-- > 0) {
            s = bf.readLine();
            result = 2;

            dfs(0, s.length()-1, false);

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int left, int right, boolean deleted) {
        if (left >= right) {
            if (!deleted) result = 0;
            else result = 1;
            return;
        }

        if (s.charAt(left) == s.charAt(right)) {
            dfs(left+1, right-1, deleted);
        } else {
            if (!deleted) {
                dfs(left+1, right, true);
                dfs(left, right-1, true);
            }
        }
    }
}