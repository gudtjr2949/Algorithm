import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parents;
    static boolean find;


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int answer = Integer.MAX_VALUE;
        parents = new int[N];
        find = false;

        for (int i = 0 ; i < N ; i++) {
            parents[i] = i;
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int small = Math.min(a, b);
            int big = Math.max(a, b);

            if (union(a, b)) {
                answer = Math.min(answer, i+1);
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    /**
     * A와 B가 연결되는데, 그 두 점의 부모가 같다? -> 두 점의 연결로 인해 사이클이 만들어짐
     */
    static boolean union(int A, int B) {
        int rootA = findRoot(A);
        int rootB = findRoot(B);

        if (rootA == rootB) {
            return true;
        } else {
            parents[rootB] = rootA;
            return false;
        }
    }

    static int findRoot(int num) {
        if (num == parents[num]) {
            return num;
        } else {
            return parents[num] = findRoot(parents[num]);
        }
    }
}