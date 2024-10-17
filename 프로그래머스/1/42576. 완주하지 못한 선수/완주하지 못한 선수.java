import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
       
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        boolean[] completed = new boolean[participant.length];
        
        for (int i = 0 ; i < completion.length ; i++) {
            if (participant[i].equals(completion[i])) {
                completed[i] = true;
            } else {
                answer = participant[i];
                break;
            }
        }
        
        if (answer.isEmpty()) {
            for (int i = 0 ; i < completed.length ; i++) {
                if (!completed[i]) {
                    answer = participant[i];
                    break;
                }
            }
        }
            
        
        return answer;
    }
}