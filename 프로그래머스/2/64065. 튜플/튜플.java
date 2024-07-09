import java.util.*;

class Solution {
    public List<Integer> solution(String s) {
        List<Integer> answer = new ArrayList<>();
        
        s = s.substring(2, s.length()-2);
        
        s = s.replaceAll("\\{", "");
        
        String[] arr = s.split("},");
        
        Arrays.sort(arr, (s1, s2) -> s1.length() - s2.length());
        
        int idx = 0;
        int maxLength = 0;
        
        for (int i = 0 ; i < arr.length ; i++) {
            String[] tmp = arr[i].split(",");
            
            for (int j = 0 ; j < tmp.length ; j++) {
                int num = Integer.parseInt(tmp[j]);
                
                if (!answer.contains(num)) {
                    answer.add(num);
                }
            }
        }
        
                
        return answer;
    }
}