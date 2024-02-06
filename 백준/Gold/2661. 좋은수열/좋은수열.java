import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static boolean findAnswer = false;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        dfs("");
    }

    static void dfs(String s) {
        if (s.length() == N) {
            System.out.println(s);
            findAnswer = true;
            return;
        }

        for (int i = 1 ; i <= 3 ; i++) {
            if (check(s+i)) {
                dfs(s+i);
                if (findAnswer) {
                    return;
                }
            }
        }
    }

    static boolean check(String s) {

        for (int i = 1 ; i <= s.length()/2 ; i++) {
            String left = s.substring(s.length()-i *2, s.length()-i);
            String right = s.substring(s.length()-i);

            if (left.equals(right)) {
                return false;
            }
        }

        return true;
    }
}