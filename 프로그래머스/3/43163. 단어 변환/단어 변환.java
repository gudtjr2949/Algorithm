import java.util.*;

class Solution {
    
    static int answer;
    static Set<String> set;
    static class Node {
        int idx;
        String s;
        public Node(int idx, String s) {
            this.idx = idx;
            this.s = s;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        
        makeSet(words);
        
        bfs(begin, target);
        
        return answer;
    }
    
    static void bfs(String begin, String target) {
        Queue<Node> PQ = new PriorityQueue<>((o1, o2) -> o1.idx - o2.idx);
        
        PQ.add(new Node(0, begin));
        
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            
            if (now.s.equals(target)) {
                answer = now.idx;
                return;
            }
            
            set.remove(now.s);
            
            for (String s : set) {
                if (check(now.s, s)) {
                    PQ.add(new Node(now.idx+1, s));
                }
            }
        } 
    }
    
    // from -> to 가능한지 확인
    static boolean check(String from, String to) {
        if (from.length() != to.length()) return false;
        
        int cnt = 0;
        
        for (int i = 0 ; i < from.length() ; i++) {
            if (from.charAt(i) != to.charAt(i)) cnt++;
        }
        
        if (cnt == 1) return true;
        return false;
    }
    
    static void makeSet(String[] words) {
        set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
    }
}