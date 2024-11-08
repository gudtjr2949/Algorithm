class Solution {
    
    public int solution(String s) {
        int answer = 0;
        
        if (s.length() == 1) return 1;
        
        Loop:
        for (int i = s.length()-1 ; i >= 0 ; i--) { // 문자열 길이
            for (int j = 0 ; j < s.length() - i ; j++) { // 시작점
                if (solve(j, j+i, s)) {
                    answer = i+1;
                    break Loop;
                }
            } 
        }
        
        return answer;
    }
    
    static boolean solve(int left, int right, String s) {
        
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        
        return true;
    }
    
}