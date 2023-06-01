import java.io.BufferedReader;
import java.io.InputStreamReader;

// 백준 15651 : N과 M (3)
public class BOJ_15651 {
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String s = bf.readLine();
		String[] s_arr = s.split(" ");

		int N = Integer.parseInt(s_arr[0]);
		int M = Integer.parseInt(s_arr[1]);

		recursive(N, new int[M], 0);
		
		System.out.println(sb);
	}

	private static void recursive(int N, int[] output, int cur) {
		if (cur == output.length) {
			String answer = "";

			for (int i = 0; i < output.length; i++) {
				answer += output[i] + " ";
			}

			sb.append(answer).append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			output[cur] = i+1;
			recursive(N, output, cur+1);
		}
	}

}
