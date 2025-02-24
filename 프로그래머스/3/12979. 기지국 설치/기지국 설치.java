class Solution {
    
    static int answer;
    
    public int solution(int n, int[] stations, int w) {
        solve(n, stations, w);
        return answer;
    }
    
    static void solve(int n, int[] stations, int w) {
        int spread = (w*2)+1;
        
        int start = 1;
        
        for (int i = 0 ; i < stations.length ; i++) {
            int end = stations[i] - w;
            
            if (start < end) {
                int cnt = end - start;

                if (cnt % spread == 0) {
                    answer += cnt / spread;
                } else {
                    answer += (cnt / spread) + 1;
                }
            }
            
            start = stations[i] + w + 1;
        }
        
        if (start <= n) {
            int cnt = (n - start) + 1;
                            
            if (cnt % spread == 0) {
                answer += cnt / spread;
            } else {
                answer += (cnt / spread) + 1;
            }
        }
        
    }
}