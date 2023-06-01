import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 9205 : 맥주 마시면서 걸어가기
public class BOJ_9205 {

    static int N;
    static Point home, festival;
    static Point[] con; // 편의점
    static int[][] map;
    static String answer;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test  = Integer.parseInt(bf.readLine());

        for (int T = 0 ; T < Test ; T++) {
            N = Integer.parseInt(bf.readLine());

            con = new Point[N];
            visited = new boolean[N];
            
            StringTokenizer st = new StringTokenizer(bf.readLine());

            home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for (int i = 0 ; i < N ; i++) {
                st = new StringTokenizer(bf.readLine());
                con[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(bf.readLine());

            festival = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            answer = "sad";

            bfs();

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
    
    private static void bfs() {
    	// Q : 현재 상근이가 이동 가능한 위치
        Queue<Point> Q = new LinkedList<>();

        Q.add(new Point(home.x, home.y));
        
        while(!Q.isEmpty()) {
        	int size = Q.size();
        	
        	for (int i = 0 ; i < size ; i++) {
        		Point p = Q.poll();
        		
        		if (Math.abs(p.y - festival.y) + Math.abs(p.x - festival.x) <= 1000) {
        			answer = "happy";
        			return;
        		}
        		
        		for (int j = 0 ; j < N ; j++) {
        			if (Math.abs(p.y - con[j].y) + Math.abs(p.x - con[j].x) <= 1000 && !visited[j]) {
        				visited[j] = true;
        				Q.add(new Point(con[j].x, con[j].y));
        			}
        		}
        	}
        }   
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }    
    }
}
