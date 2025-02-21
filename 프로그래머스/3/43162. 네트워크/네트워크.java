import java.util.*;

class Solution {
    
    static int answer;
    static int[] parents;
    static Set<Integer> set;
    
    static public int solution(int n, int[][] computers) {
        init(n);
        set(n);
        solve(n, computers);
        findAnswer(n);
        return set.size();
    }
    
    static void findAnswer(int n) {
        for (int i = 1 ; i <= n ; i++) {
            set.add(parents[i]);
        }
    }
    
    static void solve(int n, int[][] computers) {
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (i == j) continue;
                
                if (computers[i][j] == 1) {
                    union(i+1, j+1);
                }
            }
        }
        
        for (int i = 1 ; i <= n ; i++) {
            parents[i] = find(i);
        }
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
                
        if (a <= b) parents[b] = a;
        else parents[a] = b;
    }
    
    static int find(int num) {
        if (parents[num] == num) return num;
        return parents[num] = find(parents[num]);
    }
    
    static void set(int n) {
        for (int i = 0 ; i <= n ; i++) parents[i] = i;
    }
    
    static void init(int n) {
        parents = new int[n+1];
        set = new HashSet<>();
    }
}