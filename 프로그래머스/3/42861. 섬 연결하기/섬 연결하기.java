import java.util.*;

class Solution {
    
    static int answer;
    static int[] parents;
    
    public int solution(int n, int[][] costs) {
        answer = 0;
        parents = new int[n];
        for (int i = 0 ; i < n ; i++) parents[i] = i;

        Arrays.sort(costs, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        
        for (int i = 0 ; i < costs.length ; i++) {
            int x = costs[i][0];
            int y = costs[i][1];
            int cost = costs[i][2];
            
            if (findParent(x) != findParent(y)) {
                union(x, y);
                answer += cost;
            }
        }
        
        return answer;
    }
    
    static void union(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        
        if (x != y) parents[x] = y;
    }
    
    
    static int findParent(int num) {
        if (num != parents[num]) return findParent(parents[num]);
        return num;
    }
}