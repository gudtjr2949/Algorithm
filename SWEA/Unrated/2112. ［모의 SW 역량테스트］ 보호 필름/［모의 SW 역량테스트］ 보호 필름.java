import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int D, W, K, cnt;
    static boolean possible;
    static int[] input;
    static int[][] arr, originArr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());

        for (int testCase = 1 ; testCase <= Test ; testCase++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cnt = 0;
            possible = false;

            arr = new int[D][W];
            originArr = new int[D][W];

            for (int i = 0 ; i < D ; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0 ; j < W ; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    originArr[i][j] = arr[i][j];
                }
            }

            int minCnt = 0;

            for (int i = 0 ; i <= D ; i++) {
                input = new int[i];
                dfs1(0, 0);
                if (possible) {
                    minCnt = i;
                    break;
                }
            }

            sb.append("#").append(testCase).append(" ").append(minCnt).append("\n");
        }

        System.out.println(sb);
    }

    // 어느 층에 약품을 투여할 지 결정
    static void dfs1(int idx, int cur) {
        if (idx == input.length) {
            dfs2(0);
            restoration();
            return;
        }

        if (possible) return;

        for (int i = cur ; i < D ; i++) {
            input[idx] = i;
            dfs1(idx+1, i+1);
        }
    }

    // 어떤 약품을 투여할 지 결정
    static void dfs2(int idx) {
        if (idx == input.length) {
            check();
            return;
        }

        if (possible) return;

        Arrays.fill(arr[input[idx]], 0);
        dfs2(idx+1);

        Arrays.fill(arr[input[idx]], 1);
        dfs2(idx+1);
    }


    static void check() {
        int allCnt = 0;

        for (int i = 0 ; i < W ; i++) {
            int cnt = 1;
            boolean pass = false;

            for (int j = 1 ; j < D ; j++) {
                if (arr[j][i] == arr[j-1][i]) {
                    cnt++;

                    if (cnt == K) {
                        pass = true;
                        break;
                    }
                }
                else cnt = 1;
            }

            if (pass) allCnt++;
            else return;
        }

        if (allCnt == W) possible = true;
    }

    static void restoration() {
        for (int i = 0 ; i < D ; i++) {
            for (int j = 0 ; j < W ; j++) {
                arr[i][j] = originArr[i][j];
            }
        }
    }
}