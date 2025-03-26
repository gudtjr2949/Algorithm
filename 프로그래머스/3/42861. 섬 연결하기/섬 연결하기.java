import java.util.*;

class Solution {
    
    static int N, answer;
    static int[] parents;
    static int[][] bridges;
    
    public int solution(int n, int[][] costs) {
        init(n, costs);
        solve();
        return answer;
    }
    
    static void solve() {
        Arrays.sort(bridges, (o1, o2) -> o1[2] - o2[2]); // 건설 비용 오름차순 정렬
        
        for (int[] bridge : bridges) {
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
        if (num != parents[num]) return parents[num] = find(parents[num]);
        return num;
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a != b) parents[b] = a;
    }
    
    static void init(int n, int[][] costs) {
        N = n;
        
        parents = new int[N];
        for (int i = 0 ; i < N ; i++) {
            parents[i] = i;
        }
        
        bridges = new int[costs.length][3];
        for (int i = 0 ; i < costs.length ; i++) {
            bridges[i][0] = costs[i][0];
            bridges[i][1] = costs[i][1];
            bridges[i][2] = costs[i][2];
        }
    }
}