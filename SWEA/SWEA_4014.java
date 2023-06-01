
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA 4014 : 활주로 건설
public class SWEA_4014 {
    static int N, L;
    static int[][] map;
    static int answer;
    static boolean[][] bridge1, bridge2;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int Test = Integer.parseInt(bf.readLine());
        
		for (int T = 1; T <= Test; T++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			answer = 0;
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			bridge1 = new boolean[N][N];
			bridge2 = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 이동할 방향으로의 높이가 같거나, 현재위치보다 1이 작은데 그 높이가 L 칸 만큼 연속되면 이동 가능
			makeBridge();

			sb.append("#").append(T).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
    }

    // 높이가 1씩 차이나는 길 찾기
    private static void makeBridge() {

        for (int i = 0 ; i < N ; i++) {
            boolean sign = true;

            Loop:
            for (int j = 0 ; j < N-1 ; j++) {

                if (map[i][j] == map[i][j+1]) { // 가고자하는 위치의 높이가 같은 경우
                    continue;
                }
                else if (map[i][j] == map[i][j+1]+1) { // 가고자하는 위치의 높이가 1만큼 더 낮은 경우
                    for (int q = j+1 ; q <= j+L ; q++) {
                        if (q >= N || map[i][j+1] != map[i][q]) {
                            sign = false;
                            break Loop;
                        }

                        bridge1[i][q] = true;
                    }

                    j += L-1;
                }
                else if (map[i][j]+1 == map[i][j+1]) { // 가고자하는 위치의 높이가 1만큼 더 높은 경우
                    for (int q = j ; q >= j-L+1 ; q--) {
                        if (q < 0 || map[i][j] != map[i][q] || bridge1[i][q]) {
                            sign = false;
//                            System.out.println("가로 " + i);
                            break Loop;
                        }

                        bridge1[i][q] = true;
                    }
                }
                else {
                    sign = false;
                    break;
                }
            }

            if (sign) {
//                System.out.println("가로 " + i);
                answer++;
            }

            sign = true;

            Loop:
            for (int j = 0 ; j < N-1 ; j++) {

                if (map[j][i] == map[j+1][i]) { // 가고자하는 위치의 높이가 같은 경우
                    continue;
                }
                else if (map[j][i] == map[j+1][i]+1) { // 가고자하는 위치의 높이가 1만큼 더 낮은 경우
                    for (int q = j+1 ; q <= j+L ; q++) {
                        if (q >= N || map[j+1][i] != map[q][i]) {
                            sign = false;
                            break Loop;
                        }

                        bridge2[q][i] = true;
                    }

                    j += L-1;
                }
                else if (map[j][i]+1 == map[j+1][i]) { // 가고자하는 위치의 높이가 1만큼 더 높은 경우
                    for (int q = j ; q >= j-L+1 ; q--) {
                        if (q < 0 || map[j][i] != map[q][i] || bridge2[q][i]) {
                            sign = false;
                            break Loop;
                        }

                        bridge2[q][i] = true;
                    }
                }
                else {
                    sign = false;
                    break;
                }
            }

            if (sign) {
//                System.out.println("세로 "+ i);
                answer++;
            }
        }

    }
}
