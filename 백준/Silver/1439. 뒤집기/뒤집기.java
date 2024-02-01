import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();

        int zero = 0;
        int one = 0;

        // 0 뭉치
        for (int i = 0 ; i < s.length() - 1 ; i++) {
            if (s.charAt(i) == '0' && s.charAt(i+1) == '1') {
                zero++;
            }
        }

        // 1 뭉치
        for (int i = 0 ; i < s.length()-1 ; i++) {
            if (s.charAt(i) == '1' && s.charAt(i+1) == '0') {
                one++;
            }
        }


        if (s.charAt(s.length()-1) == '0') {
            zero++;
        } else {
            one++;
        }

        System.out.println(Math.min(zero, one));
    }
}