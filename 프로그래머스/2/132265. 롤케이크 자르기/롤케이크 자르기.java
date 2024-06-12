class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        int max = 0;
        
        for (int i = 0 ; i < topping.length ; i++) {
            max = Math.max(max, topping[i]);
        }
        
        int[] young = new int[max+1];
        int youngCnt = 0;
        
        int[] brother = new int[max+1];
        int brotherCnt = 0;
        
        for (int cake : topping) young[cake]++;
        
        
        for (int i = 1 ; i <= max ; i++) 
            if (young[i] != 0) youngCnt++;
        
        for (int cake : topping) {
            young[cake]--;
            brother[cake]++;
            
            if (young[cake] == 0) youngCnt--;
            if (brother[cake] == 1) brotherCnt++;
            
            if (youngCnt == brotherCnt) answer++;
        }
        
        return answer;
    }
}