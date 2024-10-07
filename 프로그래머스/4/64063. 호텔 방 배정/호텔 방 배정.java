import java.util.*;

class Solution {
    
    static Map<Long, Long> map = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        int N = room_number.length;
        
        long[] answer = new long[N];
                
        for (int i = 0 ; i < N ; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }
        
        return answer;
    }
    
    static Long findEmptyRoom(long room_number) {
        if (!map.containsKey(room_number)) {
            map.put(room_number, room_number + 1);
            return room_number;
        }
        
        long empty = findEmptyRoom(map.get(room_number));
        map.put(room_number, empty);
        return empty;
    }
}