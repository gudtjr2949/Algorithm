import java.util.*;

class Solution {
    
    static int answer = 0;
    static List<String> list = new ArrayList<>();
    static char[] c = {'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        
        dfs(word, new StringBuffer(), 0);
                
        for (int i = 0 ; i < list.size() ; i++) {
            if (word.equals(list.get(i))) {
                answer = i+1;
                break;
            }
        }
        
        return answer;
    }
    
    static void dfs(String word, StringBuffer input, int cnt) {
        if (input.length() == 5) {
            return;
        }
    
        for (int i = 0 ; i < 5 ; i++) {
            input.append(c[i]);
            list.add(input.toString());
            dfs(word, input, cnt);
            input.deleteCharAt(input.length()-1);
        }
    }
}