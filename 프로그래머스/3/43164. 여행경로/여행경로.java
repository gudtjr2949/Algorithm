import java.util.*;

class Solution {
    
    static boolean[] visited;
    static List<String> answer = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs("ICN", "ICN", tickets, 0);
        Collections.sort(answer);
        return answer.get(0).split(" ");
    }
    
    static void dfs(String next, String visit, String[][] tickets, int depth) {
        if (tickets.length == depth) {
            answer.add(visit);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(next) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], visit + " " + tickets[i][1], tickets, depth + 1);
                visited[i] = false;
            }
        }
    }
}