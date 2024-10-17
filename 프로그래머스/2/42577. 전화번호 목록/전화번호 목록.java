import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book, (o1, o2) -> o1.length() - o2.length());
        
        Set<String> set = new HashSet<>();
        
        for (String phone : phone_book) {
            for (int i = 1 ; i <= phone.length() ; i++) {
                String s = phone.substring(0, i);
                if (set.contains(s)) {
                    return false;
                }
            }
            
            set.add(phone);
        }
        
        return true;
    }
}