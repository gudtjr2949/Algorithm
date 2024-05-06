import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int[] arr;
    static int zero, one;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        arr = new int[s.length()];

        for (int i = 0 ; i < s.length() ; i++) {
            arr[i] = s.charAt(i) - '0';
        }

        zero = 0;
        one = 0;

        for (int i = 0 ; i < s.length() ; i++) {
            if (arr[i] == 0) zero++;
            else one++;
        }

        zero /= 2;
        one /= 2;

        solve();
    }

    static void solve() {
        // 1은 앞에서 부터 삭제
        int idx = 0;

        while (one > 0) {
            if (arr[idx] == 1) {
                arr[idx] = -1;
                one--;
                idx--;
            }

            idx++;
        }

        idx = arr.length - 1;

        // 0은 뒤에서 부터 삭제
        while (zero > 0) {
            if (arr[idx] == 0) {
                arr[idx] = -1;
                zero--;
                idx++;
            }

            idx--;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] != -1) sb.append(arr[i]);
        }

        System.out.println(sb.toString());
    }
}