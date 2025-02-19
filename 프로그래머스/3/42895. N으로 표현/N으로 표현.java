import java.util.*;

class Solution {
    
    static int answer = -1;
    static List<Set<Integer>> adj;
    
    public int solution(int N, int number) {
        init();
        solve(N, number);
        return answer;
    }
    
    static void solve(int N, int number) {
        adj.get(1).add(N);
        
        if (number == N) {
            answer = 1;
            return;
        }
        
        int sequenceN = N;
        
        for (int i = 2 ; i <= 8 ; i++) {
            sequenceN = sequenceN*10 + N;
            for (int j = 1 ; j <= i-1 ; j++) {
                adj.get(i).add(sequenceN);
                union(i, j, i-j);
                
                if (adj.get(i).contains(number)) {
                    answer = i;
                    return;
                }
            }
        }
    }
    
    static void union(int idx, int aIdx, int bIdx) {
        for (int a : adj.get(aIdx)) {
            for (int b : adj.get(bIdx)) {
                adj.get(idx).add(a + b);
                adj.get(idx).add(a - b);
                adj.get(idx).add(a * b);
                if (b == 0) continue;
                adj.get(idx).add(a / b);
            }
        }
    }
    
    static void init() {
        adj = new ArrayList<>();
        for (int i = 0 ; i <= 8 ; i++) adj.add(new HashSet<>());
    }
}