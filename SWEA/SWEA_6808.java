import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// SWEA 6808 : 규영이와 인영이의 카드게임
public class SWEA_6808 {
	
	static int[] KyuYoung;
	static int[] InYoung;
	
	static int KyuYoung_win;
	static int KyuYoung_lose;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());
		
		for (int i = 0 ; i < Test ; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			
			KyuYoung = new int[9];
			InYoung = new int[9];
			
			KyuYoung_win = 0;
			KyuYoung_lose = 0;
			
			boolean[] check = new boolean[18];
			
			for (int j = 0 ; j < 9 ; j++) {
				KyuYoung[j] = Integer.parseInt(s_arr[j]);
				check[KyuYoung[j]-1] = true;
			}
			
			int idx = 0;
			
			for (int j = 0 ; j < 18 ; j++) {
				if (!check[j]) {
					InYoung[idx] = j+1;
					idx++;
				}
			}
			
			perm(new int[9], new boolean[9], 0);
			
			sb.append("#").append(i+1).append(" ").append(KyuYoung_win).append(" ").append(KyuYoung_lose).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void perm(int[] input, boolean[] visited, int idx) {
		if (idx == 9) {
			solve(input);
			return;
		}
		
		for (int i = 0 ; i < 9 ; i++) {
			if (!visited[i]) {
				visited[i] = true;
				input[idx] = InYoung[i];
				perm(input, visited, idx+1);
				visited[i] = false;
			}
		}
	}
	
	private static void solve(int[] InYoung_perm) {
		int K_sum = 0;
		int I_sum = 0;
		
		for (int i = 0 ; i < 9 ; i++) {
			if (KyuYoung[i] > InYoung_perm[i]) {
				K_sum += KyuYoung[i] + InYoung_perm[i];
			}
			else if (KyuYoung[i] < InYoung_perm[i]) {
				I_sum += KyuYoung[i] + InYoung_perm[i];
			}
		}
		
		if (K_sum > I_sum)
			KyuYoung_win++;
		else if (K_sum < I_sum)
			KyuYoung_lose++;
	}

}
