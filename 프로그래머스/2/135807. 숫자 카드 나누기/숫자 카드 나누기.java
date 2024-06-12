import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int chulsu = solve(arrayA, arrayB);
        int younghee = solve(arrayB, arrayA);
        
        answer = Math.max(chulsu, younghee);
        
        return answer;
    }
    
    static int solve(int[] my, int[] others) {
        int num = my[0];
        
        for (int i = 1 ; i < my.length ; i++) num = GCD(num, my[i]);
        
        for (int other : others) 
            if (other % num == 0) return 0;
        
        return num;
        
    }
    
    // 최대 공약수
    static int GCD(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}