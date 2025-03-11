import java.util.*;

class Solution {
    
    static int N;
    static String answer;
    static String[] arr;
    
    public String solution(int[] numbers) {
        answer = "";
        
        init(numbers);
        input(numbers);
        solve();
     
        return answer;
    }
    
    static void solve() {
        Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if (arr[0].equals("0")) {
            answer = "0";
            return;
        }
        
        for (String number : arr) {
            answer += number;
        }
    }
    
    static void init(int[] numbers) {
        N = numbers.length;
        arr = new String[N];
    }
    
    static void input(int[] numbers) {
        for (int i = 0 ; i < N ; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
    }
}