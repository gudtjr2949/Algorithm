import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static boolean find;
    static String S, T;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        S = bf.readLine();
        T = bf.readLine();

        dfs(T);

        if (find) System.out.println(1);
        else System.out.println(0);
    }

    static void dfs(String t) {
        if (t.length() == S.length()) {
            if (t.equals(S)) find = true;
            return;
        }

        if (find) return;

        if (t.charAt(t.length()-1) == 'A') dfs(t.substring(0, t.length()-1));
        if (t.charAt(t.length()-1) == 'B') dfs(reverse(t.substring(0, t.length()-1)));
    }

    static String reverse(String t) {
        StringBuilder sb = new StringBuilder(t);
        return sb.reverse().toString();
    }
}