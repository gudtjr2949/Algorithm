import java.util.*;

class Solution {
    
    static Set<Integer> set = new HashSet<>();
    static boolean[] visited = new boolean[7];
    
    public int solution(String numbers) {
        int answer = 0;
        
        dfs(0, "", numbers);
        
        for (Integer num : set) {
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    static void dfs(int idx, String input, String numbers) {
        if (idx == numbers.length()) {
            return;
        }
                
        for (int i = 0 ; i < numbers.length() ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String output = input+numbers.charAt(i);
                set.add(Integer.parseInt(output));
                dfs(idx+1, output, numbers);
                visited[i] = false;
            }
        }
    }
    
    
    static boolean isPrime(int num) {
        if (num < 2){
            return false; // 1은 소수가 아님
        } else {
            for (int i = 2; i <= (int) Math.sqrt(num); i++) {
                if(num % i == 0) return false;
            }
            
            return true;
        }
    }
}