import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String s;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            s = bf.readLine();
            sb.append(solve(0, s.length()-1, false)).append("\n");
        }

        System.out.println(sb);
    }

    static int solve(int left, int right, boolean delete) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (!delete) {
                    if (solve(left, right-1, true) == 0 || solve(left+1, right, true) == 0) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    return 2;
                }
            }
        }
        return 0;
    }
}