import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 아파트_색칠하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bf.readLine());
		
		int[] memo = new int[N+1];
		
		memo[1] = 2;
		memo[2] = 3;
		
		for (int i = 3 ; i <= N ; i++) {
			memo[i] = memo[i-1] + memo[i-2]; 
		}
		
		System.out.println(memo[N]);
	}
}
