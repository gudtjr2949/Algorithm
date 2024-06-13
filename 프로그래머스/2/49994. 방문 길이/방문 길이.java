class Solution {
    
    static int answer;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean[][][] visited = new boolean[11][11][4];
    
    public int solution(String dirs) {
        answer = 0;
        
        solve(dirs);
        
        return answer;
    }
    
    static void solve(String dirs) {
        int nowX = 5;
        int nowY = 5;
        
        for (int i = 0 ; i < dirs.length() ; i++) {
            int dir = findDir(dirs.charAt(i));
            int reverseDir = findReverseDir(dirs.charAt(i));
            
            int nextX = nowX + dx[dir];
            int nextY = nowY + dy[dir];
             
            if (nextX > 10 || nextX < 0 || 
               nextY > 10 || nextY < 0) continue;
            
            if (!visited[nowY][nowX][dir] && !visited[nextY][nextX][reverseDir]) {
                answer++;
                visited[nowY][nowX][dir] = true;
            }
            
            nowX = nextX;
            nowY = nextY;
        }
    }
    
    static int findDir(char c) {
        int dir = 0;
        
        if (c == 'U') dir = 0;
        else if (c == 'R') dir = 1;
        else if (c == 'D') dir = 2;
        else dir = 3;
        
        return dir;
    }
    
    static int findReverseDir(char c) {
        int dir = 0;
        
        if (c == 'U') dir = 2;
        else if (c == 'R') dir = 3;
        else if (c == 'D') dir = 0;
        else dir = 1;
        
        return dir;
    }
}