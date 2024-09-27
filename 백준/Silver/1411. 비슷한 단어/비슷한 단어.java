import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, answer;
    static String[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new String[N];

        for (int i = 0 ; i < N ; i++) {
            arr[i] = bf.readLine();
        }

        for (int i = 0 ; i < N-1 ; i++) {
            for (int j = i+1 ; j < N ; j++) {
                answer += solve(arr[i], arr[j]);
            }
        }

        System.out.println(answer);
    }

    private static int solve(String s1, String s2) {
        char[] change = new char[52];
        Arrays.fill(change, ' ');
        boolean[] alpha = new boolean[52];

        for (int i = 0 ; i < s1.length() ; i++) {
            if (change[charToInt(s1.charAt(i))] == ' ') {
                if (alpha[charToInt(s2.charAt(i))]) return 0;

                change[charToInt(s1.charAt(i))] = s2.charAt(i);
                alpha[charToInt(s2.charAt(i))] = true;
            } else if (change[charToInt(s1.charAt(i))] != s2.charAt(i)) return 0;
        }

        return 1;
    }

    static int charToInt(char c) {
        return Character.isUpperCase(c) ? c - 'A' : c - 'a' + 26;
    }
}