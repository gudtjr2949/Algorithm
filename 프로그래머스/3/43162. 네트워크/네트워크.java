import java.util.*;
import java.io.*;

class Solution {
    
    static boolean[] visited;
    
    static public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for (int i = 0 ; i < n ; i++) {
            if (!visited[i]) {
                answer++;
                dfs(i, n, computers);
            }
        }

        return answer;
    }
    
    static void dfs(int idx, int n, int[][] computers) {
        
        for (int i = 0 ; i < n ; i++) {
            if (!visited[i] && idx != i && computers[idx][i] == 1) {
                visited[i] = true;
                dfs(i, n, computers);
            }
        }
    }
}