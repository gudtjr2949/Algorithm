import java.util.*;
import java.io.*;

class Solution {
    
    static int N, cnt;
    static int[] arr;
    
	public static void main(String args[]) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 0;
		while (T++ < 10) {
        	N = Integer.parseInt(bf.readLine());
            cnt = 0;
            
            arr = new int[N];
            
            StringTokenizer st = new StringTokenizer(bf.readLine());
            
            for (int i = 0 ; i < N ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            solve();
            
            sb.append("#").append(T).append(" ").append(cnt).append("\n");
        }
        
        System.out.println(sb);
	}
    
    static void solve() {
    	for (int i = 2 ; i < N-2 ; i++) {
            
            int max = 0;
            boolean possible = true;
            
        	for (int j = i-2 ; j <= i+2 ; j++) {
            	if (j == i) continue;
                
                if (arr[i] > arr[j]) {
                    max = Math.max(max, arr[j]);
                } else {
                	possible = false;
                    break;
                }
            }
            
            if (possible) cnt += arr[i] - max;
        }
    }
}