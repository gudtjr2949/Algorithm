import java.util.*;

class Solution {
    
    static int cutS, cutE;
    static List<List<Integer>> adj;
    
    public int solution(int n, int[][] wires) {
        int answer = 1000;
        
        adj = new ArrayList<>();
        for (int i = 0 ; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int i = 0 ; i < n-1 ; i++) {
            adj.get(wires[i][0]).add(wires[i][1]);
            adj.get(wires[i][1]).add(wires[i][0]);
        }
        
        // 나눔으로 생긴 두 송전탑 갯수의 절댓값 차이가 가장 작은 것
        for (int i = 0 ; i < n-1 ; i++) {
            cutS = wires[i][0];
            cutE = wires[i][1];
            answer = Math.min(bfs(n), answer);
        }
        
        return answer;
    }
    
    // 뭉치 갯수 계산하기
    static int bfs(int n) {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(1);
                
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        int cnt = 1;
        
        while (!Q.isEmpty()) {
            int now = Q.poll();
                                    
            for (int next : adj.get(now)) {
                if ((cutS != now || cutE != next) && (cutS != next || cutE != now) 
                    && !visited[next]) {
                    visited[next] = true;
                    cnt++;
                    Q.add(next);
                }
            }
        }
        
        return Math.abs((n-cnt) - cnt);
    }
}