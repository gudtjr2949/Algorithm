import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, L, K;
    static List<Star> list = new ArrayList<>();
    static class Star {
        int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Star(x, y));
        }

        int cnt = 0;

        for(Star node1 : list){
            for(Star node2 : list){
                cnt = Math.max(cnt, checkStar(node1.x, node2.y));
            }
        }

        System.out.println(K - cnt);
    }

    static int checkStar(int x, int y) {
        int cnt = 0;
        for(Star star : list) {
            if(x <= star.x && star.x <= x+L && y <= star.y && star.y <= y+L) cnt++;
        }
        return cnt;
    }
}