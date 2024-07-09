import java.util.*;

class Solution {
    
    static int N, M, answer = 100_001;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;
    static class Node {
        int x, y, cnt;
        
        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = maps[i][j];
            }
        }
        
        visited[0][0] = true;
        
        bfs();
        
        if (answer == 100_001) answer = -1;
        
        return answer;
    }
    
    static void bfs() {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.cnt - n2.cnt;
            }
        });
        
        visited[0][0] = true;
        PQ.add(new Node(0, 0, 1));
        
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            
            if (now.y == N-1 && now.x == M-1) {
                answer = now.cnt;
                return;
            }
            
            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx] && map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    PQ.add(new Node(nx, ny, now.cnt+1));
                }
            }
        }
    }
}