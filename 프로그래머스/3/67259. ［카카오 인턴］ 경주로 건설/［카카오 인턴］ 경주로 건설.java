import java.util.*;

class Solution {
    
    static int answer, N;
    static int[] dx = {0, 1, 0, -1}, dy = {-1 , 0, 1, 0};
    static class Node {
        int x, y, dir, line, curve;
        public Node(int x, int y, int dir, int line, int curve) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.line = line;
            this.curve = curve;
        }
    }
    
    public int solution(int[][] board) {
        answer = 0;
        N = board.length;
        bfs(board);
        return answer;
    }
    
    static void bfs(int[][] board) {
        PriorityQueue<Node> PQ = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Node n1, Node n2) {
                return (n1.line * 100 + n1.curve * 500) - (n2.line * 100 + n2.curve * 500);
            }
        });
        
        int[][] dp = new int[N][N];
        for (int i = 0 ; i < N ; i++)
            Arrays.fill(dp[i], 100_000_000);
        
        PQ.add(new Node(0, 0, 1, 0, 0));
        PQ.add(new Node(0, 0, 2, 0, 0));
        dp[0][0] = 0;
        
        while (!PQ.isEmpty()) {
            Node now = PQ.poll();
            
            if (now.x == N-1 && now.y == N-1) {
                answer = (now.line * 100 + now.curve * 500);
                return;
            }
            
            for (int i = 0 ; i < 4 ; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[ny][nx] == 0) {
                    int line = now.line;
                    int curve = now.curve;
                    if (i == now.dir) line++;
                    else {
                        line++;
                        curve++;
                    }
                    
                    if (dp[ny][nx] >= dp[now.y][now.x] + (line * 100 + curve * 500)) {
                        dp[ny][nx] = dp[now.y][now.x] + (line * 100 + curve * 500);
                        PQ.add(new Node(nx, ny, i, line, curve));
                    }
                }
            }
        }
    }
}