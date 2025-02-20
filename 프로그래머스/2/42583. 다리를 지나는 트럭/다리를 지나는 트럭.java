import java.util.*;

class Solution {
    
    static int answer;
    static Queue<Node> Q;
    static class Node {
        int time, weight;
        public Node(int time, int weight) {
            this.time = time;
            this.weight = weight;
        }
    }
    
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        init();
        solve(bridge_length, weight, truck_weights);
        return answer;
    }
    
    static void solve(int bridge_length, int weight, int[] truck_weights) {
        int time = 1;
        int idx = 1;
        int curWeight = truck_weights[0];
        Q.add(new Node(time, truck_weights[0]));
        
        while (!Q.isEmpty()) {
            time++;
            while (!Q.isEmpty() && time - Q.peek().time == bridge_length) {
                curWeight -= Q.poll().weight;
            }
            
            if (Q.size() < bridge_length && idx < truck_weights.length
                  && curWeight + truck_weights[idx] <= weight) {
                Q.add(new Node(time, truck_weights[idx]));
                curWeight += truck_weights[idx++];
            }
        }
        
        answer = time;
    }
    
    static void init() {
        Q = new LinkedList<>();
    }
}
