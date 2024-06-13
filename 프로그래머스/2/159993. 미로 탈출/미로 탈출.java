import java.util.*;

class Solution {
    
    static int N, M;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] map;
    static class Node {
        int x, y, cnt;
        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        N = maps.length;
        M = maps[0].length();
        
        map = new char[N][M];
        
        int startX = 0;
        int startY = 0;
        int exitX = 0;
        int exitY = 0;
        int leverX = 0;
        int leverY = 0;
        
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'S') {
                    startX = j;
                    startY = i;
                }
                
                if (map[i][j] == 'L') {
                    leverX = j;
                    leverY = i;
                }
                
                if (map[i][j] == 'E') {
                    exitX = j;
                    exitY = i;
                }
            }
        }
        
        int startToLever = bfs(startX, startY, leverX, leverY);
        int leverToExit = bfs(leverX, leverY, exitX, exitY);
        
        if (startToLever == 0 || leverToExit == 0) answer = -1;
        else answer = startToLever + leverToExit;
        
        return answer;
    }
    
    static int bfs(int startX, int startY, int endX, int endY) {
        boolean[][] visited = new boolean[N][M];
        
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.cnt - n2.cnt;
            }
        });
        
        PQ.add(new Node(startX, startY, 0));
        visited[startY][startX] = true;
        
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            
            if (now.x == endX && now.y == endY) {
                return now.cnt;
            }
            
            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] != 'X'
                   && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    PQ.add(new Node(nx, ny, now.cnt+1));
                }
            }
            
        }
        
        return 0;
    }
}