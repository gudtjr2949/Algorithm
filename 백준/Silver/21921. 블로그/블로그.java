import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] prefix = new int[N+1];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefix[i+1] = prefix[i] + arr[i];
        }

        int max = 0;
        int maxCnt = 0;
        
        for (int i = 1 ; i+X-1 <= N ; i++) {
            int sum = prefix[i+X-1] - prefix[i-1];
            if (max < sum) {
                max = sum;
                maxCnt = 1;
            } else if (max == sum) {
                maxCnt++;
            }
        }

        if (max == 0) System.out.println("SAD");
        else {
            System.out.println(max);
            System.out.println(maxCnt);
        }

    }
}