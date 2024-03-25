import java.util.*;

class Solution {
    
    static int answer = 0;
    static Node[] nodeWords;
    static boolean[] visited;
    static class Node {
        int idx, cnt;
        String s;
        public Node(int idx, int cnt, String s) {
            this.idx = idx;
            this.cnt = cnt;
            this.s = s;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        
        nodeWords = new Node[words.length+1];
        visited = new boolean[words.length+1];
        for (int i = 1 ; i <= words.length ; i++) {
            nodeWords[i] = new Node(i, 0, words[i-1]);
        }
        
        bfs(begin, target);
        
        return answer;
    }
    
    static void bfs(String begin, String target) {
        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(0, 0, begin));
        visited[0] = true;
        
        while (!Q.isEmpty()) {
            Node now = Q.poll();
            
            if (now.s.equals(target)) {
                answer = now.cnt;
                return;
            }
            
            for (int i = 1 ; i < nodeWords.length ; i++) {
                if (!visited[i] && possibleToChange(now.s, nodeWords[i].s)) {
                    visited[i] = true;
                    Q.add(new Node(i, now.cnt+1, nodeWords[i].s));
                }
            }
        }
    }
    
    static boolean possibleToChange(String s1, String s2) {
        int cnt = 0;
        
        for (int i = 0 ; i < s1.length() ; i++) {
            if (s1.charAt(i) != s2.charAt(i)) cnt++;
        }
        
        if (cnt > 1 || cnt == 0) return false;
        return true;
    }
}