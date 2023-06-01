import java.io.BufferedReader;
import java.io.InputStreamReader;

// SWEA 1952 : 수영장
public class SWEA_1952 {
	
	static int[] n_day = { 1, 30, 90}; // 1년은 빼자
	static int day;
	static int month;
	static int three_month;
	static int year;
	static int[] num;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int Test = Integer.parseInt(bf.readLine());

		for (int i = 0; i < Test; i++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			
			day = Integer.parseInt(s_arr[0]);
			month = Integer.parseInt(s_arr[1]);
			three_month = Integer.parseInt(s_arr[2]);
			year = Integer.parseInt(s_arr[3]);
			
			answer = year;
			
			num = new int[12];

			String s2 = bf.readLine();
			String[] s2_arr = s2.split(" ");

			for (int j = 0; j < 12; j++)
				num[j] = Integer.parseInt(s2_arr[j]);

			solve(new int[12], 0);
			
			sb.append("#").append(i+1).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	/*
	 * input : 해당 달에 사용할 이용권
	 * idx : input index
	 */

	private static void solve(int[] input, int idx) {
		if (idx >= 12) {
			answer = Math.min(sum(input), answer);
			
			return;
		}

		for (int i = 0; i < 3; i++) {
			input[idx] = n_day[i];
			solve(input, idx+1);
		}
	}
	
	private static int sum(int[] input) {
		int sum = 0;
		
		for (int i = 0 ; i < input.length ; i++) {
			if (input[i] == 1) {
				sum += day * num[i];
			}
			else if (input[i] == 30) {
				sum += month;
			}
			else {
				sum += three_month;
				i += 2;
			}
		}
		
		return sum;
	}
}
