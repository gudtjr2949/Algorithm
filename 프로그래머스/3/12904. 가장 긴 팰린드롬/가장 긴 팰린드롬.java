class Solution {
    
    public int solution(String s) {
        int answer = 0;
        
        Loop:
        for (int i = s.length() ; i >= 1 ; i--) {
            for (int j = 0 ; j < s.length() ; j++) {
                if (j+i > s.length()) break;
                
                if (check(j, j+i-1, s)) {
                    answer = i;
                    break Loop;
                }
            }
        }

        return answer;
    }
    
    static boolean check(int left, int right, String s) {
        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else return false;
        }
        
        return true;
    }
    
   
}