import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static String S, T;
    static boolean possible = false;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        S = bf.readLine();
        T = bf.readLine();

        dfs(T);

        System.out.println(possible ? 1 : 0);
    }

    static void dfs(String input) {
        if (input.length() == S.length()) {
            if (input.equals(S)) {
                possible = true;
            }
            return;
        }

        if (input.charAt(input.length()-1) == 'A')
            dfs(input.substring(0, input.length()-1));
        else
            dfs(secondOperation(input.substring(0, input.length()-1)));
    }

    static String secondOperation(String tmp) {
        String output = "";

        for (int i = tmp.length() - 1; i >= 0; i--)
            output += tmp.charAt(i);

        return output;
    }
}