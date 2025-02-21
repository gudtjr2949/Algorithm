import java.util.*;

class Solution {
    
    static int answer;
    static int[] parents;
    
    public int solution(int n, int[][] costs) {
        init(n);
        set(n);
        solve(costs);
        return answer;
    }
    
    static void solve(int[][] costs) {
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        for (int[] bridge : costs) {
            int a = bridge[0];
            int b = bridge[1];
            int cost = bridge[2];
            
            if (find(a) != find(b)) {
                union(a, b);
                answer += cost;
            }
        }
    }
    
    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a != b) parents[b] = a;
    }
    
    static void set(int n) {
        for (int i = 0 ; i < n ; i++) parents[i] = i;
    }
    
    static void init(int n) {
        parents = new int[n];
    }
}