import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int quotientFive = N / 5;
        int remainderFive = N % 5;

        int answer = 0;

        // 거슬러 줄 수 없음
        if (N == 1 || N == 3) {
            answer = -1;
        } else { // 거슬러 줄 수 있음
            if (remainderFive == 0) {
                answer = quotientFive;
            } else if (remainderFive == 1) {
                answer = (quotientFive - 1) + 3;
            } else if (remainderFive == 2) {
                answer = quotientFive + 1;
            } else if (remainderFive == 3) {
                answer = (quotientFive - 1) + 4;
            } else {
                answer = quotientFive + 2;
            }
        }

        System.out.println(answer);
    }
}