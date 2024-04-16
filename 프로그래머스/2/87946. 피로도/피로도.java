class Solution {
    
    static int max = 0;
    static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        visited = new boolean[dungeons.length];
        
        dfs(0, new int[dungeons.length], dungeons, k);
        
        answer = max;
        
        return answer;
    }
    
    static void dfs(int idx, int[] input, int[][] dungeons, int k) {
        if (idx == dungeons.length) {
            max = Math.max(max, countDungeons(input, dungeons, k));
            return;
        }
        
        for (int i = 0 ; i < dungeons.length ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                input[idx] = i;
                dfs(idx+1, input, dungeons, k);
                visited[i] = false;
            }
        }
    }
    
    static int countDungeons(int[] arr, int[][] dungeons, int k) {
        int cnt = 0;
        for (int i = 0 ; i < arr.length ; i++) {
            if (k >= dungeons[arr[i]][0]) {
                cnt++;
                k -= dungeons[arr[i]][1];
            }
        }
        
        return cnt;
    }
}