class Solution {
    public int solution(int storey) {
        int answer = 0;
        String s = Integer.toString(storey);
        
        int[] arr = new int[s.length()];
        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = s.charAt(i) - '0';
        }
        
        for (int i = arr.length-1; i >= 0 ; i--) {
            int num = arr[i];
            
            if (num < 5) {
                answer += num;
            } else if (num > 5) {
                answer += 10-num;
                if (i == 0) answer++;
                else arr[i-1]++;
            } else {
                if (i > 0 && arr[i-1] >= 5) {
                    answer += 5; // +1
                    arr[i-1]++;
                } else {
                    answer += 5; // -1
                }
            }
        }
        
        return answer;
    }
}