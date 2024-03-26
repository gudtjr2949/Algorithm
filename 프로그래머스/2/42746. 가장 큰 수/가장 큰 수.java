import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] numbersArr = new String[numbers.length];
        for (int i = 0 ; i < numbers.length ; i++) {
            numbersArr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(numbersArr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        
        if (numbersArr[0].equals("0")) return "0";
        
        for (int i = 0 ; i < numbers.length ; i++) {
            answer += numbersArr[i];
        }
                
        return answer;
    }
}