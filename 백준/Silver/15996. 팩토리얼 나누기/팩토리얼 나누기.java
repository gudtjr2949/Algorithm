import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());

        int answer = 0;
        int result = A;

        while (N / result != 0) {
            N /= result;
            answer += N;
        }

        System.out.println(answer);
    }
}