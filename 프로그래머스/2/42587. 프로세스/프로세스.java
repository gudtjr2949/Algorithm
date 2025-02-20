import java.util.*;

class Solution {
    
    static int answer;
    static Queue<Node> Q;
    static Queue<Integer> PQ;
    static class Node {
        int priority, idx;
        Node(int priority, int idx) {
            this.priority = priority;
            this.idx = idx;
        }
    }
    
    
    public int solution(int[] priorities, int location) {
        answer = 0;
        init();
        set(priorities);
        solve(location);
        return answer;
    }
    
    static void solve(int location) {
        int cnt = 0;
        while (true) {
            if (Q.peek().priority == PQ.peek()) {
                cnt++;
                Node now = Q.poll();
                PQ.poll();
                if (now.idx == location) {
                    answer = cnt;
                    return;
                }
            } else {
                Node now = Q.poll();
                Q.add(now);
            }
        }
    }
    
    static void set(int[] priorities) {
        for (int i = 0 ; i < priorities.length ; i++) {
            Q.add(new Node(priorities[i], i));
            PQ.add(priorities[i]);
        }
    }
    
    static void init() {
        Q = new LinkedList<>();
        PQ = new PriorityQueue<>(Collections.reverseOrder());
    }
}