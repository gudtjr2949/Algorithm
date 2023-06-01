import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2042 : 구간 합 구하기
public class BOJ_2042 {
	
	static int N, M, K;
	static long[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int depth = 0;
		
		while(Math.pow(2, depth) < N) {
			depth++;
		}
		
		depth++;
		
		tree = new long[(int)Math.pow(2, depth)];
		
		int start = (int)Math.pow(2, depth-1);
		
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(bf.readLine());
            long in = Long.parseLong(st.nextToken());
			tree[start + i] = in;
		}
		
		// 합 구하기
		for (int i = start - 1 ; i >= 1 ; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
		
		// 수 변경
		for (int i = 0 ; i < M + K ; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			// 수 변경
			if (a == 1) {
				int target_idx = (int) (b + start - 1);
				long diff = c - tree[target_idx];
//				tree[target_idx] = c;
				
				while(target_idx > 0) {
					tree[target_idx] += diff;
					target_idx /= 2;
				}
			} else {
				int s = (int)b + start - 1;
				int e = (int)c + start - 1;
				
				long answer = 0;
				
				while(s <= e) {
					if (s % 2 == 1) answer += tree[s];
					if (e % 2 == 0) answer += tree[e];
					
					s = (s + 1) / 2;
					e = (e - 1) / 2;
				}
				
				sb.append(answer).append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
