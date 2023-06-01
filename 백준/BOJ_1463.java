import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 1463 : 1로 만들기
public class BOJ_1463 {
	
	static int N;
	static int answer;
	static int[] memo;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		memo = new int[N+1];
		
		solve();
		
		System.out.println(memo[N]-1);
	}

	private static void solve() {
		
		for (int i = 1 ; i <= N ; i++) {
			
			memo[i] = memo[i-1]+1;
			
			if (i % 3 == 0) {
				// i까지 오는데 사용한 연산 횟수 = Math.min(i까지 오는데 사용한 연산 횟수, i/3까지 오는데 사용한 연산 횟수 + 1)
				memo[i] = Math.min(memo[i], memo[i/3]+1);
			}
			
			if (i % 2 == 0) {
				// i까지 오는데 사용한 연산 횟수 = Math.min(i까지 오는데 사용한 연산 횟수, i/2까지 오는데 사용한 연산 횟수 + 1)
				memo[i] = Math.min(memo[i], memo[i/2]+1);
			}
		}
	}
}
