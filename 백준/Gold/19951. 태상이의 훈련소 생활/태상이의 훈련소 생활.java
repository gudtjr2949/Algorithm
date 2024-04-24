import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        int[] prefix = new int[N+2];

        st = new StringTokenizer(bf.readLine());

        for (int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            prefix[a] += k;
            prefix[b+1] += k*-1;
        }

        for (int i = 1 ; i <= N ; i++) {
            prefix[i] += prefix[i-1];
        }

        for (int i = 1 ; i <= N ; i++) {
            arr[i] += prefix[i];
            System.out.print(arr[i]+ " ");
        }

    }
}