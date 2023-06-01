import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 백준 14500 : 테트로미노
public class BOJ_14500 {
	
	static int[][][] nx =
		{
			{ // 1번 도형
				{0, 1, 1, 1}, 
				{0, 0, 0, 0}
			}, 
				
			{ // 2번 도형
				{0, 1, 0, -1}
			}, 
				
			{ // 3번 도형
				{0, 0, 0, 1}, 
				{0, -1, 0, 0},
				{0, 0, 0, -1}, 
				{0, 1, 0, 0}, 
				{0, 1, 1, 0}, 
				{0, 0, 1, 1}, 
				{0, 0, -1, -1},
				{0, 0, 1, 1}
			},
			
			{ // 4번 도형
				{0, 0, 1, 0},
				{0, 0, -1, 0}, 
				{0, 1, 0, 1}, 
				{0, 1, 0, 1},
			},
				
			{ // 5번 도형
				{0, 0, 1, -1},
				{0, 0, -1, 1},
				{0, 1, 0, 1},
				{0, 1, 0, 1}
			}
		};
	
	static int[][][] ny = 
		{
			{ // 1번 도형
				{0, 0, 0, 0},
				{0, 1, 1, 1}
			},
			
			{ // 2번 도형
				{0, 0, 1, 0}
			},
			
			{ // 3번 도형
				{0, 1, 1, 0},
				{0, 0, 1, 1},
				{0, 1, 1, 0},
				{0, 0, 1, 1},
				{0, 0, 0, 1},
				{0, -1, 0, 0},
				{0, 1, 0, 0},
				{0, 1, 0, 0}
			},
			
			{ // 4번 도형
				{0, 1, 0, 1},
				{0, 1, 0, 1},
				{0, 0, -1, 0},
				{0, 0, 1, 0}
			},
			
			{ // 5번 도형
				{0, 1, 0, 1},
				{0, 1, 0, 1},
				{0, 0, 1, -1},
				{0, 0, -1, 1}
			}
		};
	
	static int N, M;
	static int[][] map;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		answer = Integer.MIN_VALUE;
		
		for (int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int y = 0 ; y < N ; y++) {
			for (int x = 0 ; x < M ; x++) {
				for (int i = 0 ; i < 5 ; i++) {
					for (int j = 0 ; j < nx[i].length ; j++) {
						solve(x, y, i, j);
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	/*
	 x : x 좌표
	 y : y 좌표
	 num : 도형 번호
	 idx : 해당 도형의 방향벡터 번호
	 */
	private static void solve(int x, int y, int num, int idx) {
		int sum = 0;
		
		for (int i = 0 ; i < nx[num][idx].length ; i++) {
			x += nx[num][idx][i];
			y += ny[num][idx][i];
			
			if (x >= M || x < 0 || y >= N || y < 0) {
				return;
			}
			
			sum += map[y][x];
		}
		
		answer = Math.max(answer, sum);
	}
}
