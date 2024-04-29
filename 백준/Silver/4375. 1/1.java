import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        String s = "";
        while ((s = bf.readLine()) != null) {
            int num = Integer.parseInt(s);
            int remainder = 1;
            int cnt = 1;

            while (remainder % num != 0){
                remainder = (remainder * 10 + 1) % num;
                cnt++;
            }

            answer.append(cnt).append("\n");
        }

        System.out.println(answer);
    }
}