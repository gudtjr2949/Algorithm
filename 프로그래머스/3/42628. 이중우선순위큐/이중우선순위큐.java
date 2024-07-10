import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> min = new PriorityQueue<>((i1, i2) -> i1 - i2); // 최소 힙
        PriorityQueue<Integer> max = new PriorityQueue<>((i1, i2) -> i2 - i1); // 최대 힙
        
        int length = 0;
        boolean possible = true;
        
        for (String oper : operations) {
            int num = Integer.parseInt(oper.split(" ")[1]);
            
            if (oper.charAt(0) == 'I') {
                max.add(num);
                min.add(num);
            } else {
                // 둘 다 비어있는 경우, 연산 무시
                if (max.isEmpty() && min.isEmpty()) continue;
                
                if (num == -1) {
                    int del = min.poll();
                    max.remove(del);
                } else {
                    int del = max.poll();
                    min.remove(del);
                }
            }
        }
        
        if (max.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = max.poll();
            answer[1] = min.poll();
        }
        
        return answer;
    }
}