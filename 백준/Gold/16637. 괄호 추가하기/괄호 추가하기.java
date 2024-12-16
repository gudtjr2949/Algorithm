import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N, answer = Integer.MIN_VALUE;
    static List<Integer> nums;
    static List<Character> operations;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        nums = new ArrayList<>();
        operations = new ArrayList<>();

        String s = bf.readLine();

        for (int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                operations.add(c);
            } else {
                nums.add(c - '0');
            }
        }

        dfs(nums.get(0), 0);

        System.out.println(answer);
    }

    static void dfs(int result, int idx) {
        if (idx >= operations.size()) {
            answer = Math.max(answer, result);
            return;
        }


        if (idx + 1 < operations.size()) {
            int result2 = cal(operations.get(idx+1), nums.get(idx+1), nums.get(idx+2));
            dfs(cal(operations.get(idx), result, result2), idx+2);
        }

        dfs(cal(operations.get(idx), result, nums.get(idx+1)), idx+1);
    }

    static int cal(char operation, int a, int b) {
        if (operation == '+') return a + b;
        else if (operation == '-') return a - b;
        return a * b;
    }
}