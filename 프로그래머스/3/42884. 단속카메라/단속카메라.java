import java.util.*;

class Solution {
    
    static class Route {
        int start, end;
        public Route(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public int solution(int[][] routes) {
        int answer = 0;
        
        Route[] route = new Route[routes.length];
        
        for (int i = 0 ; i < routes.length ; i++) {
            route[i] = new Route(routes[i][0], routes[i][1]);
        }
        
        Arrays.sort(route, new Comparator<Route>() {
            @Override
            public int compare(Route r1, Route r2) {
                return r1.end - r2.end;
            }
        });
        
        int camera = -30001;
        
        for (Route r : route) {
            if (r.start > camera) {
                camera = r.end;
                answer++;
            }
        }
        
        return answer;
    }
}