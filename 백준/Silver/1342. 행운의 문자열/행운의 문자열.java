import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int answer;
    static String s;
    static int[] alpha;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        s = bf.readLine();

        alpha = new int[27];

        for (int i = 0 ; i < s.length() ; i++) alpha[s.charAt(i) - 'a']++;

        dfs(' ', 0);

        System.out.println(answer);
    }

    static void dfs(char pre, int idx) {
        if (idx == s.length()) {
            answer++;
            return;
        }

        for (int i = 0; i < 27; i++) {
            if (alpha[i] > 0 && pre != (char) (i+'a')) {
                alpha[i]--;
                dfs((char) (i+'a'), idx+1);
                alpha[i]++;
            }
        }
    }

}