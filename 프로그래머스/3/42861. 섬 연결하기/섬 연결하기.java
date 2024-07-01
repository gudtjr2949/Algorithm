import java.util.*;

class Solution {
    
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n+1];
        
        for (int i = 0 ; i <= n ; i++) parent[i] = i;
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        
        for (int i = 0 ; i < costs.length ; i++) {
            int x = costs[i][0];
            int y = costs[i][1];
            int cost = costs[i][2];
            
            if (findParents(x) != findParents(y)) {
                union(x, y);
                answer += cost;
            }
        }
        
        return answer;
    }
    
    static void union(int x, int y) {
        x = findParents(x);
        y = findParents(y);
        
        if (x != y) parent[x] = y;
    }
    
    static int findParents(int num) {
        if (num == parent[num]) return num;
        return findParents(parent[num]);
    }
}