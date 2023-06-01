import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

// SWEA 1218 : 괄호 짝짓기
public class SWEA_1218 {

	static char[] open = { '{', '[', '(', '<' };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[] open_cnt = new int[127];

		for (int i = 0; i < 4; i++) {
			open_cnt[open[i]]++;
		}

		for (int i = 0; i < 10; i++) {
			int N = Integer.parseInt(bf.readLine());
			String s = bf.readLine();

			Stack<Character> stack = new Stack<>();

			boolean sign = true;

			int answer = 1;

			for (int j = 0; j < s.length(); j++) {
				if (open_cnt[s.charAt(j)] == 1)
					stack.add(s.charAt(j));
				else { // 닫는 괄호
					if (stack.peek() == '{' && s.charAt(j) == '}')
						stack.pop();
					else if (stack.peek() == '[' && s.charAt(j) == ']')
						stack.pop();
					else if (stack.peek() == '(' && s.charAt(j) == ')')
						stack.pop();
					else if (stack.peek() == '<' && s.charAt(j) == '>')
						stack.pop();
					else {
						sign = false;
						break;
					}
				}
			}

			if (!sign)
				answer = 0;

			sb.append("#").append(i + 1).append(" ").append(answer).append("\n");

		}
		System.out.println(sb);
	}
}
