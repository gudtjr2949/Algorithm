import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 2252 : 줄 세우기
public class BOJ_2252 {
	
	static int N, M;
	static int[] arr;
	static ArrayList<ArrayList<Integer>> list;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		list = new ArrayList<>();
		
		for (int i = 0 ; i < N+1 ; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list.get(from).add(to);
			
			arr[to]++;
		}
		
		solve();
		
		System.out.println(sb);
	}

	private static void solve() {
		Queue<Integer> Q = new LinkedList<Integer>();

		for (int i = 1 ; i <= N ; i++) {
			if (0 == arr[i]) {
				Q.add(i);
			}
		}
		
		while(!Q.isEmpty()) {
			int num = Q.poll();
			sb.append(num).append(" ");
			
			for (int i = 0 ; i < list.get(num).size() ; i++) {
				arr[list.get(num).get(i)]--;
				
				if (arr[list.get(num).get(i)] == 0) {
					Q.add(list.get(num).get(i));
				}
			}
		}
	}
}
