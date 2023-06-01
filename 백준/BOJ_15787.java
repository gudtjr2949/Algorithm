import java.io.*;
import java.util.*;

// 백준 15787 : 기차가 어둠을 헤치고 은하수를
public class BOJ_15787 {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String nm = bf.readLine();
		
		String[] nm_arr = nm.split(" ");
		
		int N = Integer.parseInt(nm_arr[0]);
		int M = Integer.parseInt(nm_arr[1]);
	
		int[] order = new int[M];
		int[] i = new int[M];
		int[] x = new int[M];
		
		int[][] train = new int[N][20];
		int[][] change_train = new int[N][20];
		
		for (int j = 0 ; j < M ; j++) {
			String s = bf.readLine();
			String[] s_arr = s.split(" ");
			order[j] = Integer.parseInt(s_arr[0]);
			if (order[j] <= 2) {
				i[j] = Integer.parseInt(s_arr[1]) - 1;
				x[j] = Integer.parseInt(s_arr[2]) - 1;
			}
			else {
				i[j] = Integer.parseInt(s_arr[1]) - 1;
			}
		}
		
		for (int j = 0 ; j < M ; j++) {
			if (order[j] == 1) {
				train[i[j]][x[j]] = 1;
			}
			else if (order[j] == 2) {
				train[i[j]][x[j]] = 0;
			}
			else if (order[j] == 3) {
				for (int q = 0 ; q < 19 ; q++) {
					change_train[i[j]][q+1] = train[i[j]][q];
				}
				
				for (int q = 0 ; q < 20 ; q++) {
					train[i[j]][q] = change_train[i[j]][q];
					change_train[i[j]][q] = 0;
				}
			}
			else {
				for (int q = 19 ; q >= 1 ; q--) {
					change_train[i[j]][q-1] = train[i[j]][q];
				}
				
				for (int q = 0 ; q < 20 ; q++) {
					train[i[j]][q] = change_train[i[j]][q];
					change_train[i[j]][q] = 0;
				}
			}
		}
		
		String[] string_train = new String[N];
		Set<String> set = new HashSet<String>();
		
		for (int j = 0 ; j < N ; j++) {
			string_train[j] = "";
			for (int q = 0 ; q < 20 ; q++) {
				string_train[j] += train[j][q];
			}
			set.add(string_train[j]);
		}
		
		System.out.println(set.size());
	}
}
