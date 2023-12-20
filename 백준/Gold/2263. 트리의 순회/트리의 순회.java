import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] inOrder, postOrder, inOrderIdx;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        inOrder = new int[N];
        postOrder = new int[N];

        StringTokenizer inSt = new StringTokenizer(bf.readLine());
        StringTokenizer postSt = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            inOrder[i] = Integer.parseInt(inSt.nextToken());
            postOrder[i] = Integer.parseInt(postSt.nextToken());
        }

        inOrderIdx = new int[N+1];
        for (int i = 0 ; i < N ; i++) {
            inOrderIdx[inOrder[i]] = i;
        }

        solve(0, N-1, 0, N-1);

        System.out.println(sb);
    }

    static void solve(int inStart, int inEnd, int postStart, int postEnd) {
        if (inEnd < inStart || postEnd < postStart) return;

        int root = postOrder[postEnd];
        int rootIdx = inOrderIdx[root];
        sb.append(root).append(" ");


        int length = rootIdx - inStart;

        // 왼쪽 트리
        solve(inStart, rootIdx-1, postStart, postStart + length - 1);

        // 오른쪽 트리
        solve(rootIdx+1, inEnd, postStart + length, postEnd-1);
    }
}