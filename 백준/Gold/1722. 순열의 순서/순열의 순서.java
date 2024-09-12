import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 *
	 *   12
	 *   순열 N=6  6! 123456 ~ 654321
	 *   5 3 2 1 4 6   => 몇번째 인지???
	 *
	 *   1 x x x x x => 5!
	 *   2 x x x x x => 5!
	 *
	 *   1)  빼면서 생각
	 *
	 *   1 5!-
	 *   2 5!-
	 *   .
	 *   6.5! -
	 *
	 *   6*5! = 6!
	 *
	 *   5! > 12
	 *
	 *   1
	 */
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1. 입력 및 초기화
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.parseInt(in.readLine()); // 1~20
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int num = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		boolean[] visited = new boolean[21];
		long[] f = new long[21];
		f[0] = 1;
		for(int i=1; i<21; i++) f[i] = f[i-1] * i; // 팩토리올 가짓수 (1! ~ 20! 총 경우의수 저장해놈)
		
		/*  N = 4
		 *  f[]  2 4 1 3
		 *
		 *  1 2 3 4
		 *  1 2 4 3
		 *  1 3 2 4
		 *  1 3 4 2
		 *  1 4 2 3
		 *  1 4 3 2
		 *
		 *  6가지를 구해야되 => 1 x x x일떄 3!
		 *
		 *  for(i=1; i<2; i++) ==>   1 x x x의 모든 경우의 수를 구해야한다.
		 *
		 *  if(!visited[i]) K += f[3]
		 *
		 *  visited[2] = true ;
		 *
		 *  for(i=1; i<4; i++) ==>   2 1 x x의 모든 경우의 수를 구해야한다.
		 *  						 2 4 x x 까지
		 *
		 *  if(!visited[i]) K += f[2]
		 *
		 *
		 *  // 1 2 3 4 일떄 1부터 시작한다.
		 *
		 */
		
		// 2. 순열찾기
		if(num == 1) { // K번째 순열 (최대 20! long으로 받음)
			long K = Long.parseLong(st.nextToken()); // N=4(1234~4321) 총24가지  K=12
			
			for(int i=0; i<N; i++) { // 팩토리얼 가짓수 치기
				for(int j=1; j<=N; j++) { // 1~N 확인
					// 순열에 이미 존재하는 숫자면 넘어간다
					if(visited[j]) continue;
					
					// 팩토리얼 값이 k보다 작으면 k에서 팩토리얼 값을 빼준다
					if(f[N-i-1] < K) K -= f[N-i-1];
					
					// 팩토리얼 값이 k보다 크면 해당하는 원소의 숫자를 찾은 것.
	                // a[i]에 저장하고 순열에 존재하는 숫자를 체크해준다
					else {
						arr[i] = j; // 2 3 4 1
						visited[j] = true;
						break;
					}
				}
			}
			
			// Answer
			for(int i=0; i<N; i++) System.out.print(arr[i]+" ");
			
		} else {	 // 몇번째 순열
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			} // 2 4 1 3
			
			/*
			 *   2 => 3! + (1)
			 *   4 => 2! + 2! + (2 3)
			 *   1 => 1!
			 *   3 => 0
			 */
			
			long ans = 1L;
			
			for(int i=0; i<N; i++) {
				for(int j=1; j<arr[i]; j++) {
					// 1부터 해당하는 원소까지 팩토리얼 값을 늘려가며 더해준다.
					if(!visited[j]) {
						ans += f[N-i-1];
					}
				}
				// 순열에 존재하는 숫자는 있다고 표시해준다.
				visited[arr[i]] = true;
			}
			
			// Answer
			System.out.println(ans);
			
		}
		
	}
	
}