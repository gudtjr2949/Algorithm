import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int M, answer;
    static String s;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while ((M = Integer.parseInt(bf.readLine())) != 0) {
            s = bf.readLine();
            answer = 0;
            solve();
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        int left = -1;
        int right = -1;

        int[] check = new int[128];

        int cnt = 0; // 사용한 자판 수
        
        while (left <= right) {
            if (right+1 >= s.length()) break;

            // 사용한 자판의 수가 M보다 작거나, M개의 자판이 필요한 상태지만, 다음 자판은 이미 사용한 적이 있는 자판이라면
            if (cnt < M || (cnt == M && check[s.charAt(right+1)] >= 1)) {
                right++;
                check[s.charAt(right)]++;
                if (check[s.charAt(right)] == 1) cnt++;
            } else {
                left++;
                check[s.charAt(left)]--;
                if (check[s.charAt(left)] == 0) cnt--;
            }

            answer = Math.max(answer, right - left);
        }
    }
}