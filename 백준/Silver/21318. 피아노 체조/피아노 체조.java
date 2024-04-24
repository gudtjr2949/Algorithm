import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N+1];
        int[] prefix = new int[N+1];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1 ; i <= N ; i++) {
            if (arr[i] < arr[i-1]) prefix[i] = prefix[i-1]+1;
            else prefix[i] = prefix[i-1];
        }

        int Q = Integer.parseInt(bf.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < Q ; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(prefix[y] - prefix[x]).append("\n");
        }

        System.out.println(sb);
    }
}