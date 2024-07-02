class Solution {
    
    static String S;
    
    public int solution(String s) {
        int answer = 0;
        
        S = s;
        
        Loop:
        for (int i = s.length() ; i >= 1 ; i--) {
            for (int j = 0 ; j < s.length() ; j++) {
                if (j + i > s.length()) break;
                
                if (check(j, j+i-1)) {
                    answer = i;
                    break Loop;
                }
            }
        }

        return answer;
    }
    
    static boolean check(int start, int end) {
        while (start <= end) {
            if (S.charAt(start) == S.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        
        return true;
    }
}