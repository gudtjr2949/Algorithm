import java.util.*;
import java.lang.Math.*;

class Solution {
    
    public String solution(String number, int k) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = 0;
        
        while (right < number.length() && sb.length() != number.length()-k) {
            right = k + sb.length() + 1;
            int max = 0;
            
            for (int i = left ; i < right ; i++) {
                if (max < number.charAt(i) - '0') {
                    max = number.charAt(i) - '0';
                    left = i+1;
                }
            }
            
            sb.append(Integer.toString(max));
        }
        
        answer = sb.toString();
        
        return answer;
    }
}