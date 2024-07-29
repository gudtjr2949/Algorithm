import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] dist;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N][N];

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0 ; j < N ; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0 ; k < N ; k++) {
            for(int i = 0 ; i < N ; i++) {
                if(k == i) continue;
                for(int j = 0 ; j < N ; j++){
                    if(i == j || j == k) continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]) dist[i][j] = dist[i][k] + dist[k][j];

                }
            }
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken())-1;
            int B = Integer.parseInt(st.nextToken())-1;
            int C = Integer.parseInt(st.nextToken());

            if (dist[A][B] > C) sb.append("Stay here").append("\n");
            else sb.append("Enjoy other party").append("\n");
        }

        System.out.println(sb);
    }
}