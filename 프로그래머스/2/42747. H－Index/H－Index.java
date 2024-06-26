import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Integer[] arr = new Integer[citations.length];
        for (int i = 0 ; i < arr.length ; i++) 
            arr[i] = citations[i];
        
        Arrays.sort(arr, Collections.reverseOrder());
        
        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] >= i+1) {
                answer = Math.max(answer, i+1);
            }
        }
        
        return answer;
    }
}