import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String S, T;
    static boolean possible;

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

        if (input.charAt(0) == 'B') {
            dfs(secondOperation(input));
        }

        if (input.charAt(input.length()-1) == 'A') {
            dfs(input.substring(0, input.length() - 1));
        }
    }

    static String secondOperation(String input) {
        String output = "";

        for (int i = input.length() - 1; i >= 0; i--) output += input.charAt(i);

        return output.substring(0, output.length() - 1);
    }
}