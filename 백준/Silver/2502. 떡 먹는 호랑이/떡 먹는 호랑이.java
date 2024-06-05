import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // A 계수 구하기
        int A = dfsA(D);
        int B = dfsB(D);

        int answerA = 0;
        int answerB = 0;

        // A에 1씩 넣어보기
        for (int i = 1 ; i <= K ; i++) {
            if ((K - (A * i)) % B == 0) {
                answerA = i;
                answerB = (K - (A * i)) / B;
                break;
            }
        }

        System.out.println(answerA);
        System.out.println(answerB);
    }

    static int dfsA(int day) {
        if (day == 1) return 1;
        if (day == 2) return 0;

        return dfsA(day-2) + dfsA(day-1);
    }

    static int dfsB(int day) {
        if (day == 1) return 0;
        if (day == 2) return 1;

        return dfsB(day-2) + dfsB(day-1);
    }
}