import java.util.*;

class Solution {
    
    static int N, M;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][] visited;
    
    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(String[] maps) {        
        N = maps.length;
        M = maps[0].length();
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0 ; i < N ; i++) {
            String s = maps[i];
            for (int j = 0 ; j < M ; j++) {
                if (s.charAt(j) == 'X') {
                    map[i][j] = -1;
                } else {
                    map[i][j] = s.charAt(j) - '0';
                }
            }
        }
        
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (!visited[i][j] && map[i][j] != -1) {
                    list.add(bfs(j, i));
                }
            }
        }
        
        // System.out.println(list);
        
        int[] answer = new int[list.size()];
        
        Collections.sort(list);
        
        if (list.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = list.stream()
                .mapToInt(i -> i)
                .toArray();
        }
        
        return answer;
    }
    
    static int bfs(int x, int y) {
        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(x, y));
        visited[y][x] = true;
        int sum = map[y][x];
        
        while(!Q.isEmpty()) {
            Node now = Q.poll();
            
            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx >= 0 && nx < M && ny >= 0 && ny < N 
                    && !visited[ny][nx] && map[ny][nx] != -1) {
                    sum += map[ny][nx];
                    visited[ny][nx] = true;
                    Q.add(new Node(nx, ny));
                }
            }
        }
                
        return sum;
    }
}