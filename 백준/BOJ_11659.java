package coding_test.백준;

import java.io.*;

// 백준 11659 : 구간 합 구하기 4
public class BOJ_11659 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		
		String nm = bf.readLine();
		String[] nm_arr = nm.split(" ");
		
		int N = Integer.parseInt(nm_arr[0]);
		int M = Integer.parseInt(nm_arr[1]);
		
		String s = bf.readLine();
		String[] s_arr = s.split(" ");
		
		int[] arr = new int[N+1];
	
		arr[0] = 0;
		
		for (int i = 0 ; i < N ; i++) {
			arr[i+1] = Integer.parseInt(s_arr[i]);
		}
		
		int[] start = new int[M];
		int[] end = new int[M];
		
		for (int i = 0 ; i < M ; i++) {
			String se = bf.readLine();
			String[] se_arr = se.split(" ");
			start[i] = Integer.parseInt(se_arr[0]);
			end[i] = Integer.parseInt(se_arr[1]);
		}
		
		for (int i = 1 ; i <= N ; i++) {
			arr[i] = arr[i-1] + arr[i];
		}
		
		for (int i = 0 ; i < M ; i++) {
			System.out.println(arr[end[i]] - arr[start[i] - 1]);
		}
	}
}
