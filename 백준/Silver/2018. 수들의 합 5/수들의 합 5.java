import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        // 공차는 반드시 1, a1(등차수열의 첫번째 항)은 매번 바뀜
        binarySearch();

        System.out.println(answer + 1);
    }

    static void binarySearch() {
        long a1 = 1;

        while (a1 <= N/2) {
            long sum = check(a1);

            if (sum == N) {
                answer++;
            }

            a1++;
        }
    }

    static long check(long mid) {
        long sum = 0;
        long num = 1; // 항의 갯수
        while (sum < N) {
            sum = ((num * num) + num * (2 * mid - 1)) / 2;
            num++;
        }

        return sum;
    }
}