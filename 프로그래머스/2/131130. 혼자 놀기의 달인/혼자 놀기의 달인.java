import java.util.*;

class Solution {
    
    static int cnt;
    static int[] arr;
    static boolean[] visited;
    
    public int solution(int[] cards) {
        int answer = 0;
        copy(cards);
        visited = new boolean[cards.length+1];
        List<Integer> list = new ArrayList<>();
        for (int i = 1 ; i < arr.length ; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cnt = 1;
                dfs(i);
                list.add(cnt);
            }
        }
        
        Collections.sort(list, Collections.reverseOrder());
        
        if (list.size() <= 1) {
            answer = 0;
        } else {
            answer = list.get(0) * list.get(1);
        }
    
        
        return answer;
    }

    
    static void dfs(int idx) {
        if (!visited[arr[idx]]) {
            visited[arr[idx]] = true;
            cnt++;
            dfs(arr[idx]);
        }
    }
    
    static void copy(int[] cards) {
        arr = new int[cards.length+1];
        for (int i = 1 ; i <= cards.length ; i++) {
            arr[i] = cards[i-1];
        }
    }
}