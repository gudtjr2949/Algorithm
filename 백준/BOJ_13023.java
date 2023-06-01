import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 백준 13023 : ABCDE
public class BOJ_13023 {

	static int N;
	static int M;
	static ArrayList<Integer>[] list;
	static boolean sign;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String nm = bf.readLine();
		String[] nm_arr = nm.split(" ");

		N = Integer.parseInt(nm_arr[0]);
		M = Integer.parseInt(nm_arr[1]);

		list = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			list[Integer.parseInt(s_arr[0])].add(Integer.parseInt(s_arr[1]));
			list[Integer.parseInt(s_arr[1])].add(Integer.parseInt(s_arr[0]));
		}

		for (int i = 0; i < N; i++) {
			Collections.sort(list[i]);
		}
		
		for (int i = 0; i < N; i++) {
			int[] input = new int[N];
			input[0] = i;
			dfs(1, i, input, new boolean[N]);
			
			if (sign) {
				break;
			}
		}
		
		if (sign)
			System.out.println(1);
		else
			System.out.println(0);
	}

	private static void dfs(int idx, int cur, int[] input, boolean[] visited) {
		if (idx >= 5) {
			sign = true;
			return;
		}

		visited[cur] = true;
	

		for (int i = 0; i < list[cur].size(); i++) {
			if (!visited[list[cur].get(i)]) {
				visited[list[cur].get(i)] = true;
				input[idx] = list[cur].get(i);
				dfs(idx + 1, list[cur].get(i), input, visited);
				visited[list[cur].get(i)] = false;
			}
		}
	}
}
