import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int answer = 1;
        int P = 0;
        int K = 0;
        if (s.charAt(0) == 'P') P++;
        else K++;

        for (int i = 1 ; i < s.length() ; i++) {
            if (s.charAt(i) == 'P') {
                P++;
                if (K > 0) K--;
            } else {
                K++;
                if (P > 0) P--;
            }

            answer = Math.max(answer, Math.max(P, K));
        }

        System.out.println(answer);
    }
}