import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// SWEA 1228 : 암호문1
public class SWEA_1228 {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0 ; i < 10 ; i++) {
			int N = Integer.parseInt(bf.readLine());
			
			String[] arr = new String[N];
			
			String s = bf.readLine();
			arr = s.split(" ");
			ArrayList<String> list = new ArrayList<>();
			
			for (int j = 0 ; j < arr.length ; j++) {
				list.add(arr[j]);
			}
			
			
			int order_N = Integer.parseInt(bf.readLine());
			
			String s_2 = bf.readLine();
			String[] s_2_arr = s_2.split(" ");
			
			for (int j = 0 ; j < s_2_arr.length ; j++) {
				if (s_2_arr[j].equals("I")) {
					int idx = Integer.parseInt(s_2_arr[j+1]);
					for (int q = j + 3 ; q < (j+3) + Integer.parseInt(s_2_arr[j+2]) ; q++) {
						if (idx >= N) {
							break;
						}
						
						list.add(idx, s_2_arr[q]);
						idx++;
					}
				}
			}
			
			
			sb.append("#").append(i+1).append(" ").append(print(list)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static String print(ArrayList<String> list) {
		String s = "";
		for (int i = 0 ; i < 10 ; i++) {
			s += list.get(i) + " ";
		}
		
		return s;
	}
}
