import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, nowStrict, max, maxStrict;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        double tmp = Double.parseDouble(bf.readLine());

        if (tmp >= 1.98) nowStrict = 2;
        else if (tmp >= 0.99) nowStrict = 1;
        else nowStrict = 0;

        N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        arr = new int[N+1]; // 필요한 스트릭 프리즈를 보관하는 배열

        for (int i = 1 ; i <= N ; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = arr[i-1] + (num == 0 ? 1 : 0);
            max = Math.max(max, num);
        }

        solve();

        System.out.println(maxStrict);
        System.out.println(max);
    }

    static void solve() {
        int start = 1;
        int end = 1;

        while (end <= N) {
            int needStrict = arr[end] - arr[start-1];

            if (needStrict > nowStrict) start++;
            else {
                maxStrict = Math.max(maxStrict, end - start + 1);
                end++;
            }
        }

    }
}