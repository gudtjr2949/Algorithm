import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int MAX = 1_001;
    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(bf.readLine());

        result = new int[MAX];

        setResult();

        StringBuilder sb = new StringBuilder();

        while (C-- > 0) {
            int N = Integer.parseInt(bf.readLine());

            sb.append(result[N]).append("\n");
        }

        System.out.println(sb);
    }

    private static void setResult() {
        result[1] = 3;

        for (int i = 2 ; i < MAX ; i++) {
            int cnt = 0;
            for (int j = 1 ; j < i ; j++) {
                if (GCD(i, j) == 1) cnt+=2;
            }

            result[i] = result[i-1] + cnt;
        }
    }

    private static int GCD(int a, int b) {
        if (a % b == 0)
            return b;

        return GCD(b, a % b);
    }
}