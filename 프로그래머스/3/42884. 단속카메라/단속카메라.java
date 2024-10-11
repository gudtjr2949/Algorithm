import java.util.*;

class Solution {
    
    static class Node {
        int start, end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public int solution(int[][] routes) {
        int answer = 0;
        
        Node[] cars = new Node[routes.length];
        for (int i = 0 ; i < routes.length ; i++) {
            cars[i] = new Node(routes[i][0], routes[i][1]);
        }
        
        Arrays.sort(cars, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.end - n2.end;
            }
        });
        
        int camera = -30001;
        for (Node car : cars) {
            if (camera < car.start) {
                camera = car.end;
                answer++;
            }
        }
        
        return answer;
    }
}