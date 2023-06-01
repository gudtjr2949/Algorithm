import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 15649 : N과 M (1)
public class BOJ_15649 {
	
	static int cnt;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String s = bf.readLine();
		String[] s_arr = s.split(" ");
		
		int N = Integer.parseInt(s_arr[0]);
		int M = Integer.parseInt(s_arr[1]);
		
		int[] arr = new int[N];
		
		recursive(N, new int[M], new boolean[N], 0);
		
		System.out.println(cnt);
	}

	private static void recursive(int N, int[] output, boolean[] visited, int idx) {
		// TODO Auto-generated method stub
		if (idx == output.length) {
			String answer = "";
			for (int i = 0 ; i < output.length ; i++) {
				answer += output[i] + " ";
			}
			System.out.println(answer);
			cnt++;
			return;
		}
		
		for (int i = 0 ; i < N ; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				output[idx] = i+1;
				recursive(N, output, visited, idx+1);
				visited[i] = false;
			}
		}
		
	}
	
}
