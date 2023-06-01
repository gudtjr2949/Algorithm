package coding_test.백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 11404 : 플로이드
public class BOJ_11404 {

    static int N, M;
    static long[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());

        dist = new long[N+1][N+1];

        for (int i = 0 ; i < N+1 ; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[from][to] = Math.min(dist[from][to], cost);
        }

        for (int k = 1 ; k < N+1 ; k++) { // 거쳐가는 정점
            for (int i = 1 ; i < N+1 ; i++) { // 출발 정점
                for (int j = 1 ; j < N+1 ; j++) { // 도착 정점
                    if (k != i && k != j && i != j) {
                        // dist[y][x]에는 y에서 x로 바로 가는 경우와 y에서 k를 거쳐 x로 가는 경우 중에서 가중치가 작은 값을 넣는다
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for (int i = 1 ; i < N+1 ; i++) {
            for (int j = 1 ; j < N+1 ; j++) {
                if (dist[i][j] == Integer.MAX_VALUE){
                    System.out.print("0" + " ");
                }
                else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
