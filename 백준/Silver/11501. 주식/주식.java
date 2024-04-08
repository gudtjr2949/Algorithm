import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Test = Integer.parseInt(bf.readLine());
        while (Test-- > 0) {
            N = Integer.parseInt(bf.readLine());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(bf.readLine());

            for (int i = 0 ; i < N ; i++) arr[i] = Integer.parseInt(st.nextToken());

            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }

    static long solve() {
        int max = arr[N-1];

        long sum = 0; // 이득

        for (int i = N-2 ; i >= 0 ; i--) {
            if (arr[i] < max) { // 주식 사야함
                sum += (max - arr[i]);
            } else if (arr[i] > max){
                max = arr[i];
            }
        }

        return sum;
    }

}