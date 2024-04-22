import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] A = new int[N+1];
        int[] prefixSum = new int[N+1];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        prefixSum[0] = 0;

        for (int i = 1 ; i <= N ; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i-1] + A[i];
        }

        int M = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken());
            sb.append(prefixSum[to] - prefixSum[from]).append("\n");
        }

        System.out.println(sb);
    }
}