import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static char[] operation = {'+', '-', ' '};
    static List<String> list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int Test = Integer.parseInt(bf.readLine());

        for (int T = 0 ; T < Test ; T++) {
            N = Integer.parseInt(bf.readLine());
            list = new ArrayList<>();
            dfs(1, "1");
            Collections.sort(list);

            for (String s : list) {
                sb.append(s).append("\n");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int idx, String s) {
        if (idx == N) {
            String input = s.replace(" ", "");
            if (calculation(input)) {
                list.add(s);
            }
            return;
        }

        dfs(idx+1, s + '+' + (idx+1));
        dfs(idx+1, s + '-' + (idx+1));
        dfs(idx+1, s + ' ' + (idx+1));
    }

    static boolean calculation(String s) {
        StringTokenizer st = new StringTokenizer(s, "-|+", true);

        int sum = Integer.parseInt(st.nextToken());

        int len = st.countTokens();

        while (st.hasMoreTokens()){
            char operation = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());

            if (operation == '+') {
                sum += num;
            } else {
                sum -= num;
            }
        }

        if (sum == 0) {
            return true;
        } else {
            return false;
        }
    }
}