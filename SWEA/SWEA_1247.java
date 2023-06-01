import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 1247 : 최적 경로
public class SWEA_1247 {
	static int min;
	static int home_x;
	static int home_y;
	
	static int office_x;
	static int office_y;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int Test = Integer.parseInt(bf.readLine());

		for (int i = 1 ; i <= Test ; i++) {
			min = Integer.MAX_VALUE;
			
			int N = Integer.parseInt(bf.readLine());
			
			char[][] board = new char[100][100];
			
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			
			office_x = Integer.parseInt(s_arr[0]);
			office_y = Integer.parseInt(s_arr[1]);
			
			home_x = Integer.parseInt(s_arr[2]);
			home_y = Integer.parseInt(s_arr[3]);
			
			
			Custormer[] custormers = new Custormer[N];
			
			int idx = 0;
			
			for (int j = 4 ; j < s_arr.length - 1 ; j+=2) {
				custormers[idx] = new Custormer(Integer.parseInt(s_arr[j]), Integer.parseInt(s_arr[j+1]));
				idx++;
			}
			
			recursive(N, custormers, new boolean[N], new int[N], 0);
			
			sb.append("#").append(i).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void recursive(int N, Custormer[] custormers, boolean[] visited, int[] output, int cur) {
		if (cur == N) {
			int sum = Math.abs(office_x - custormers[output[0]].x) + Math.abs(office_y - custormers[output[0]].y);
			for (int i = 0 ; i < N-1 ; i++) {
				sum += Math.abs(custormers[output[i]].x - custormers[output[i+1]].x) + Math.abs(custormers[output[i]].y - custormers[output[i+1]].y);
			}
			
			sum += Math.abs(custormers[output[N-1]].x - home_x) + Math.abs(custormers[output[N-1]].y - home_y);
			
			min = Math.min(min, sum);
			
			return;
		}
		
		for (int i = 0 ; i < N ; i++) {
			if (visited[i] == false) {
				visited[i] = true;
				output[cur] = i;
				recursive(N, custormers, visited, output, cur + 1);
				visited[i] = false;
			}
		}
	}
	
	
}

class Custormer {
	int x;
	int y;
	
	public Custormer() {}
	
	public Custormer(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
}
