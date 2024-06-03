import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();

        int answer = s.length();

        for (int i = 0 ; i < s.length() ; i++) {
            if (check(s.substring(i))) break;
            answer++;
        }

        System.out.println(answer);
    }

    static boolean check(String input) {
        int left = 0;
        int right = input.length() - 1;

        while (left <= right) {
            if (input.charAt(left) != input.charAt(right)) return false;

            right--;
            left++;
        }

        return true;
    }
}